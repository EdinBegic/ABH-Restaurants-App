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
        try {
            return ResponseEntity.ok(service.findAllCitiesForCountry(country));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity allCountries() {
        try {
            return ResponseEntity.ok(service.findAllCountries());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity topLocations(@RequestParam Integer size) {
        try {
            return ResponseEntity.ok(service.getTopLocations(size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
