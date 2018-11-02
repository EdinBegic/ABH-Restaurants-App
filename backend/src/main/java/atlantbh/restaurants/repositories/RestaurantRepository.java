package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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
}
