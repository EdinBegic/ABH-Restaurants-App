package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Reservation;
import atlantbh.restaurants.models.RestaurantTable;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.dto.ReservationDTO;
import atlantbh.restaurants.models.dto.ReservationResponseDTO;
import atlantbh.restaurants.models.filters.ReservationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import atlantbh.restaurants.repositories.ReservationRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService extends BaseService<Reservation, ReservationSortKeys, ReservationFilterBuilder, ReservationRepository> {
    private static final long ONE_MINUTE_IN_MILLIS = 60000;
    private static final int STAYING_TIME_MINUTES = 60;
    private static final int OFFSET = 15;
    private static final int TIME_RANGE = 120;
    private static final int MAX_TABLES = 1;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantTableService restaurantTableService;

    public static Date formatDate(String startDate, String startTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.parse(startDate + "-" + startTime);
    }

    @Transactional
    @Override
    public Reservation update(Long id, Reservation data) throws ServiceException {
        List<Reservation> reservations = null;
        try {
            reservations = repository.availableReservations(
                    data.getRestaurantTable().getRestaurant().getId(), data.getRestaurantTable().getSittingPlaces(),
                    data.getStartTime(), data.getUser(), data.getCreatedAt(), data.getConfirmed(), MAX_TABLES);
        } catch (RepositoryException e) {
            throw  new ServiceException("Error while executing SQL query.", e);
        }
        if (reservations.isEmpty()) {
            throw new ServiceException("Reservation not completed in time. Somebody already reserved in that time slot.");
        }
        return super.update(id, data);
    }

    public List<Reservation> create(ReservationDTO reservationDTO) {
        try {
            User user = null;
            if (reservationDTO.getUserId() != null) {
                user = userService.get(reservationDTO.getUserId());
            }
            Date startTime = formatDate(reservationDTO.getStartDate(), reservationDTO.getStartTime());
            Date currentDate = new Date();
            if (startTime.before(currentDate)) {
                throw new ServiceException("Requested reservation time already passed");
            }
            Date stayingPeriod = new Date(startTime.getTime() + STAYING_TIME_MINUTES * ONE_MINUTE_IN_MILLIS);
            Date createdAt = Calendar.getInstance().getTime();
            List<Reservation> reservations = repository
                    .availableReservations(reservationDTO.getRestaurantId(), reservationDTO.getSittingPlaces(),
                            startTime, user, createdAt, reservationDTO.getConfirmed(), MAX_TABLES);
            if (reservations.isEmpty()) {
                throw new RepositoryException("All tables in that time slot are already reserved.");
            }
            for (Reservation r : reservations) {
                repository.save(r);
            }
            return reservations;
        } catch (ServiceException | ParseException | RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public ReservationResponseDTO suggestedTimes(ReservationDTO reservationDTO, Integer numOfDates) throws ParseException {
        Date startTime = formatDate(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(new ArrayList<>(), 0, new ArrayList<>());
        addSuggestedTimes(startTime, reservationDTO.getRestaurantId(), reservationDTO.getSittingPlaces(),
                numOfDates, reservationDTO.getUserId(), true, reservationDTO.getConfirmed(), reservationResponseDTO);
        numOfDates -= reservationResponseDTO.getSuggestedDates().size();
        addSuggestedTimes(startTime, reservationDTO.getRestaurantId(), reservationDTO.getSittingPlaces(),
                numOfDates, reservationDTO.getUserId(), false, reservationDTO.getConfirmed(), reservationResponseDTO);
        if (reservationResponseDTO.getSuggestedDates().size() == 0) {
            throw new ServiceException("All reservation times are booked for that time period");
        }
        Collections.sort(reservationResponseDTO.getSuggestedDates());
        return reservationResponseDTO;
    }

    /***
     *
     * @param startTime requested reservation time in 'yyyy-MM-dd-HH:mm:ss' format
     * @param numOfDates how much suggested reservation dates should be returned
     * @param negativeOffset if true, suggested dates will be (if there are any) before the requested reservation time
     * @param reservationResponseDTO object which will be returned - contains suggested dates and the number of available tables
     * @return
     */
    private ReservationResponseDTO addSuggestedTimes(Date startTime, Long restaurantId, Integer sittingPlaces,
                                                     Integer numOfDates, Long userId, Boolean negativeOffset,
                                                     Boolean confirmed,
                                                     ReservationResponseDTO reservationResponseDTO) {
        List<Date> suggestedDates = new ArrayList<>();
        Integer shiftedTime = OFFSET;
        while (shiftedTime <= TIME_RANGE) {
            if (negativeOffset) {
                shiftedTime = -shiftedTime;
            }
            Date suggestedStartingTime = new Date(startTime.getTime() + shiftedTime * ONE_MINUTE_IN_MILLIS);
            Date currentDate = new Date();
            User user = userService.get(userId);
            List<Reservation> reservations = null;
            try {
                reservations = repository.availableReservations(restaurantId, sittingPlaces, suggestedStartingTime,
                        user, currentDate, confirmed, MAX_TABLES);
            } catch (RepositoryException e) {
                throw new ServiceException("Error while executing SQL query", e);
            }
            if (!reservations.isEmpty() && suggestedStartingTime.after(currentDate)) {
                List<RestaurantTable> tables = new ArrayList<>();
                for (Reservation suggestedReservation : reservations) {
                    if (!suggestedDates.contains(suggestedStartingTime)  // two different tables can have free spot in same time slot
                            && suggestedDates.size() < numOfDates) {
                        suggestedDates.add(suggestedStartingTime);
                    }
                    tables.add(suggestedReservation.getRestaurantTable());
                }
                List<List<RestaurantTable>> allTableCombinations = reservationResponseDTO.getRestaurantTables();
                allTableCombinations.add(tables);
                reservationResponseDTO.setRestaurantTables(allTableCombinations);
            }
            if (negativeOffset) {
                shiftedTime = -shiftedTime;
            }
            shiftedTime += OFFSET;
        }
        reservationResponseDTO.getSuggestedDates().addAll(suggestedDates);
        reservationResponseDTO.setAvailableTables(reservationResponseDTO.getRestaurantTables().size());
        return reservationResponseDTO;
    }
}
