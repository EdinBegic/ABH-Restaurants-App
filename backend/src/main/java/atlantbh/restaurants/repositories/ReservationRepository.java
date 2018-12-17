package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Reservation;
import atlantbh.restaurants.models.RestaurantTable;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.filters.ReservationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationRepository extends BaseRepositoryImpl<Reservation, ReservationSortKeys, ReservationFilterBuilder> {

    private static final long ONE_MINUTE_IN_MILLIS = 60000;
    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    public List<Reservation> availableReservations(Long restaurantId, Integer sittingPlaces, Date startTime,
                                                   User user, Date createdAt, Boolean confirmed, Integer maxCapacity) throws RepositoryException {
        // calculate staying period
        String query = ""
                + "WITH predicted_end_time as ( "
                + "  SELECT ROUND(AVG(extract(epoch from (r.staying_period - r.start_time))/60)/15)*15 median, "
                + "        COUNT(r.*) reservation_size "
                + "  FROM reservation r, restaurant_table rt "
                + "  WHERE ((?1)::::time >= r.start_time::::time AND (?1)::::time <= r.staying_period::::time) "
                + "    AND r.restaurant_table_id=rt.id AND rt.restaurant_id=?2 "
                + "    AND rt.sitting_places = ?3 "
                + "    AND r.confirmed = true) "
                + "SELECT  (CASE WHEN p.reservation_size < 10 "
                + "            THEN "
                + "                (SELECT ROUND(AVG(extract(epoch from (r.staying_period - r.start_time))/60)/15)*15 median "
                + "                FROM reservation r, restaurant_table rt, restaurant rest "
                + "                WHERE ((?1)::::time >= r.start_time::::time AND (?1)::::time <= r.staying_period::::time) "
                + "                  AND r.restaurant_table_id=rt.id "
                + "                  AND rt.sitting_places = ?3 "
                + "                  AND r.confirmed = true) "
                + "        ELSE "
                + "            p.median "
                + "        END) as end_time "
                + "          FROM predicted_end_time p; ";
        NativeQuery q = getSession().createNativeQuery(query);
        q.setParameter(1, startTime);
        q.setParameter(2, restaurantId);
        q.setParameter(3, sittingPlaces);
        Double d = (Double) q.getSingleResult();
        Integer stayingPeriod = d.intValue();
        // Check if any tables are free in that time slot
        query = ""
                + "With table_cap as (SELECT ?1 max_capacity) "
                + "SELECT rt.* "
                + "FROM table_cap tc, restaurant_table rt, restaurant rest "
                + "WHERE "
                + "       rt.restaurant_id=rest.id AND rest.id = ?2 "
                + "       AND "
                + "       (rt.sitting_places - ?3 <= 2) "
                + "       AND "
                + "       tc.max_capacity - "
                + "                      (SELECT COUNT(r.*) "
                + "                        FROM reservation r "
                + "                        WHERE r.restaurant_table_id = rt.id AND "
                + "                            (((?4)::::timestamp >= r.start_time AND (?4)::::timestamp <= r.staying_period) "
                + "                            OR((?4)::::timestamp + (?5 * interval '1 minute') >= r.start_time "
                + "                                  AND (?4)::::timestamp + (?5 * interval '1 minute') <= r.staying_period) "
                + "                            )     AND r.confirmed=true "
                + "                        ) > 0 "
                + "     ORDER BY rt.sitting_places ASC "
                + " ; ";
        q = getSession().createNativeQuery(query);
        q.setParameter(1, maxCapacity);
        q.setParameter(2, restaurantId);
        q.setParameter(3, sittingPlaces);
        q.setParameter(4, startTime);
        q.setParameter(5, stayingPeriod);
        List<Object[]> at = q.getResultList();
        List<RestaurantTable> availableTables = new ArrayList<>();
        for (int i = 0; i < at.size(); i++) {
            BigInteger idInDb = (BigInteger) at.get(i)[0];
            availableTables.add(restaurantTableRepository.get(idInDb.longValue()));
        }
        if (availableTables.isEmpty()) {
            return new ArrayList<>();
        }
        Integer maxMinTable = 0;
        int maxMinId = -1;
        int index = 0;
        Date endTime = new Date(startTime.getTime() + stayingPeriod * ONE_MINUTE_IN_MILLIS);
        for (RestaurantTable rt : availableTables) {
            if (rt.getSittingPlaces() == sittingPlaces) {
                List<Reservation> list = new ArrayList<>();
                list.add(new Reservation(startTime, endTime, user, rt, createdAt, confirmed));
                return list;
            }
            // if table with requested sitting places, not available, try the least bigger table
            if (rt.getSittingPlaces() > sittingPlaces) {
                if (maxMinTable == 0 || rt.getSittingPlaces() < maxMinTable) {
                    maxMinTable = rt.getSittingPlaces();
                    maxMinId = index;
                }
            }
            index++;
        }
        List<Reservation> list = new ArrayList<>();
        if (maxMinId != -1) {
            list.add(new Reservation(startTime, endTime, user, availableTables.get(maxMinId), createdAt, confirmed));
            return list;
        }
        int smallestDifference = 2;
        // if only smaller tables are available, suggest them
        int firstTableIndex = -1;
        int secondTableIndex = -1;
        for (int i = 0; i < availableTables.size(); i++) {
            for (int j = 0; j < availableTables.size(); j++) {
                int difference = Math.abs(availableTables.get(i).getSittingPlaces()
                        + availableTables.get(j).getSittingPlaces()) - sittingPlaces;
                if (i != j && difference <= smallestDifference) {
                    firstTableIndex = i;
                    secondTableIndex = j;
                    smallestDifference = difference;
                }
            }
        }
        list.add(new Reservation(startTime, endTime, user, availableTables.get(firstTableIndex), createdAt, confirmed));
        list.add(new Reservation(startTime, endTime, user, availableTables.get(secondTableIndex), createdAt, confirmed));
        return list;
    }
}
