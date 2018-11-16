package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Reservation;
import atlantbh.restaurants.models.filters.ReservationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.TimeZone;

@Repository
public class ReservationRepository extends BaseRepositoryImpl<Reservation, ReservationSortKeys, ReservationFilterBuilder> {

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    public Boolean isAvailable(Long restaurantTableId, Date startTime, Date stayingPeriod) {
        Criteria criteria = getBaseCriteria().createAlias("restaurantTable", "rt");
        criteria.add(Restrictions.eq("rt.id", restaurantTableId));
        criteria.add(Restrictions.eq("confirmed", true));
        Criterion criterion = Restrictions.or(Restrictions.and(Restrictions.le("startTime", startTime),
                Restrictions.gt("stayingPeriod", startTime)),
                Restrictions.and(Restrictions.lt("startTime", stayingPeriod),
                        Restrictions.ge("stayingPeriod", stayingPeriod)));
        criteria.add(criterion);
        Long numOfReservationsInSelectedTime = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return numOfReservationsInSelectedTime == 0;
    }

}
