package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.PaginatedResult;
import atlantbh.restaurants.models.Reservation;
import atlantbh.restaurants.models.RestaurantTable;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.dto.ReservationResponseDTO;
import atlantbh.restaurants.models.filters.ReservationFilterBuilder;
import atlantbh.restaurants.models.filters.RestaurantTableFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ReservationRepository extends BaseRepositoryImpl<Reservation, ReservationSortKeys, ReservationFilterBuilder> {

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    public Boolean isAvailable(Long restaurantTableId, Date startTime, Date stayingPeriod) {
        Criteria criteria = getBaseCriteria();
        criteria.createAlias("restaurantTable", "rt");
        criteria.add(Restrictions.eq("rt.id", restaurantTableId));
        Criterion criterion = Restrictions.or(Restrictions.and(Restrictions.le("startTime", startTime),
                Restrictions.gt("stayingPeriod", startTime)),
                Restrictions.and(Restrictions.lt("startTime", stayingPeriod),
                        Restrictions.ge("stayingPeriod", stayingPeriod)));
        criteria.add(criterion);
        Long numOfReservationsInSelectedTime = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        if (numOfReservationsInSelectedTime == 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public Reservation save(User user, Long restaurantId, Integer sittingPlaces, Date startTime, Date stayingPeriod) throws RepositoryException {
        PaginatedResult<RestaurantTable> tableList = restaurantTableRepository.find
                (new RestaurantTableFilterBuilder().setSittingPlaces(sittingPlaces).setRestaurantId(restaurantId));
        List<RestaurantTable> restaurantTables = tableList.getData();
        int counter = 0;
        Reservation r = null;
        for (RestaurantTable rt : restaurantTables) {
            if (isAvailable(rt.getId(), startTime, stayingPeriod)) {
                counter++;
                r = new Reservation(startTime, stayingPeriod, user, rt);
            }
        }
        if (counter == 0) {
            throw new RepositoryException("All tables in that time slot are already reserved");
        }
        return super.save(r);
    }
}
