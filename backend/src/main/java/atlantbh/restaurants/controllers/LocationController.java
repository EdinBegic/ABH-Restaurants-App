package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController extends BaseController<Location, LocationService> {

    public ResponseEntity allCities(@PathVariable String country) {
        return ResponseEntity.ok(service.findAllCitiesForCountry(country));
    }
    public ResponseEntity allCountries() {
        return ResponseEntity.ok(service.findAllCountires());
    }

}
