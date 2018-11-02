package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.repositories.RestaurantRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RestaurantService extends BaseService<Restaurant, RestaurantSortKeys, RestaurantFilterBuilder, RestaurantRepository> {

    public Collection<Restaurant> getPopularRestaurants(Integer numOfRestaurants) {
        try {
            return repository.getPopularRestaurants(numOfRestaurants);
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't retrive popular restaurants", e);
        }
    }
}
