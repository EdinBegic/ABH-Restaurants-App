package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.filters.LocationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import atlantbh.restaurants.repositories.LocationRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LocationService extends BaseService<Location, LocationSortKeys, LocationFilterBuilder, LocationRepository> {

    // TODO Change method to use Criteria instead of native query
    public List<Location> findAllCitiesForCountry(String country) {
        try {
            return repository.findAllCitiesForCountry(country);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    // TODO Change method to use Criteria instead of native query
    public List<Location> findAllCountries() {
        try {
            return repository.findAllCountries();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Collection<Location> getTopLocations(Integer numOfLocations) throws Exception {
        try {
            return repository.getTopLocations(numOfLocations);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
