package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.OrderBySqlFormula;
import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.spatial.criterion.SpatialRestrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class RestaurantRepository extends BaseRepositoryImpl<Restaurant, RestaurantSortKeys, RestaurantFilterBuilder> {
    public Collection<Restaurant> getPopularRestaurants(Integer numOfRestaurants) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RANDOM()"));
            criteria.setMaxResults(numOfRestaurants);
            return criteria.list();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public List<Restaurant> getNearestRestaurants(Point point, Double latitude, Double longitude) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.add(SpatialRestrictions.distanceWithin("coordinates", point, 0.05));
            // To order by a custom SQL formula, i created an own class which extends Order
            // guide on http://blog.tremend.ro/how-to-order-by-a-custom-sql-formulaexpression-when-using-hibernate-criteria-api/
            String customOrder = "ST_Distance(coordinates, ST_Geomfromtext('POINT(" + latitude + " " + longitude + ")'))";
            criteria.addOrder(OrderBySqlFormula.sqlFormula(customOrder));
            return criteria.list();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
