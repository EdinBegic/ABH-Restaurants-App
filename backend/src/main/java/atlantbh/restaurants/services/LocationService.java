package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService extends BaseService<Location, LocationRepository> {

    public Location findLocationByCountryAndCity(String country, String city) {
        return repo.findByCountryAndCity(country,city);
    }
    public List<Location> findAllCitiesForCountry(String country) {
        List<Location> list = repo.findAllCitiesByCountry(country);
        return list;
    }
    public List<Location> findAllCountires() {
        List<Location> list = repo.findAllCountires();
        return list;
    }

}
