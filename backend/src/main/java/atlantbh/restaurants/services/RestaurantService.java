package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.dto.CoordinatesDTO;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.repositories.RestaurantRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
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

    public Integer getNearestRestaurants(Double latitude, Double longitude) throws ServiceException {
        try {
            GeometryFactory gf = new GeometryFactory();
            Point point = gf.createPoint(new Coordinate(latitude, longitude, 0.0));
            return repository.getNearestRestaurants(point, latitude, longitude).size();
        } catch (RepositoryException e) {
            throw new ServiceException("Could not locate restaurants near by.", e);
        }
    }

    public CoordinatesDTO getCoordinates(Long id) {
        try {
            Restaurant r = repository.get(id);
            CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
            coordinatesDTO.setLatitude(r.getCoordinates().getX());
            coordinatesDTO.setLongitude(r.getCoordinates().getY());
            return coordinatesDTO;
        } catch (RepositoryException e) {
            throw new ServiceException("Could not retrieve location coordinates from selected restaurant", e);
        }
    }
}
