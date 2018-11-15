package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController extends BaseController<Location, LocationService> {

    @GetMapping("/country/{id}")
    public ResponseEntity allCities(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.findAllCitiesForCountry(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/distinct/countries")
    public ResponseEntity allCountries() {
        try {
            return ResponseEntity.ok(service.findAllCountries());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/top")
    public ResponseEntity topLocations(@RequestParam Integer size) {
        try {
            return ResponseEntity.ok(service.getTopLocations(size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
