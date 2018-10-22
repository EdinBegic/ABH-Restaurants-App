package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.repositories.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends BaseService<Location, LocationRepository> {

    Location findLocationByCountryAndCity(String country, String city) {
        return repo.findByCountryAndCity(country,city);
    }
}
