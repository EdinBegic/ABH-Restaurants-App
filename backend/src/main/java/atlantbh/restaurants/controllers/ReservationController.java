package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Reservation;
import atlantbh.restaurants.models.dto.ReservationDTO;
import atlantbh.restaurants.models.filters.ReservationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import atlantbh.restaurants.services.ReservationService;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/reservations")
public class ReservationController extends BaseController<Reservation, ReservationService> {

    @PostMapping("/request")
    public ResponseEntity create(@RequestBody @Valid ReservationDTO reservationDTO) {
        try {
            return ResponseEntity.ok(service.create(reservationDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/suggested-times")
    public ResponseEntity suggestedTimes(@RequestBody @Valid ReservationDTO reservationDTO, @RequestParam(value = "numOfDates") Integer numOfDates) {
        try {
            return ResponseEntity.ok((service.suggestedTimes(reservationDTO, numOfDates)));
        } catch (ServiceException e) {
            return ResponseEntity.noContent().build();
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity reservationHistory(@RequestParam(value = "userId") Long userId,
                                             @RequestParam(value = "sortKey", required = false) String sortKey,
                                             @RequestParam(value = "sortAsc", required = false) Boolean sortAsc,
                                             @RequestParam(value = "pageNumber") Integer pageNumber,
                                             @RequestParam(value = "pageSize") Integer pageSize) {
        try {
            ReservationFilterBuilder rfb = new ReservationFilterBuilder()
                    .setUserId(userId)
                    .setPageNumber(pageNumber)
                    .setPageSize(pageSize)
                    .setConfirmed(true)
                    .setSortKey(EnumUtils.getEnum(ReservationSortKeys.class, sortKey))
                    .setSortAsc(sortAsc);
            return ResponseEntity.ok(service.filter(rfb));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/size-for-period")
    public ResponseEntity numOfReservationsForPeriod(@RequestParam(value = "startTime") String startTime,
                                                     @RequestParam(value = "finishTime") String finishTime,
                                                     @RequestParam(value = "startDate") String startDate,
                                                     @RequestParam(value = "finishDate") String finishDate,
                                                     @RequestParam(value = "restaurantId") Long restaurantId,
                                                     @RequestParam(value = "sortKey", required = false) String sortKey,
                                                     @RequestParam(value = "sortAsc", required = false) Boolean sortAsc) {
        try {
            Date start = ReservationService.formatDate(startDate, startTime);
            Date finish = ReservationService.formatDate(finishDate, finishTime);
            ReservationFilterBuilder rfb = new ReservationFilterBuilder()
                    .setStartTime(start)
                    .setFinishTime(finish)
                    .setRestaurantId(restaurantId)
                    .setConfirmed(true)
                    .setSortKey(EnumUtils.getEnum(ReservationSortKeys.class, sortKey))
                    .setSortAsc(sortAsc);
            return ResponseEntity.ok(service.filter(rfb).getAvailable());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @PostMapping
    @Override
    public ResponseEntity create(@RequestBody Reservation model) {
        return super.create(model);
    }

    @PutMapping
    @Override
    public ResponseEntity update(@RequestParam Long id, @RequestBody Reservation model) {
        return super.update(id,model);
    }


    }
