package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.filters.LocationFilterBuilder;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import atlantbh.restaurants.services.LocationService;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.service.spi.ServiceException;
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
    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "country", required = false) String country,
                                 @RequestParam(value = "city", required = false) String city,
                                 @RequestParam(value = "sortKey", required = false) String sortKey,
                                 @RequestParam(value = "sortAsc", required = false) Boolean sortAsc) {
        try {
            LocationFilterBuilder lfb = new LocationFilterBuilder()
                    .setCountry(country)
                    .setCity(city)
                    .setPageSize(pageSize)
                    .setPageNumber(pageNumber)
                    .setSortAsc(sortAsc)
                    .setSortKey(EnumUtils.getEnum(LocationSortKeys.class, sortKey));
            return ResponseEntity.ok(service.filter(lfb));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity update(@PathVariable Long id, @RequestBody Location model) {
        try {
            return ResponseEntity.ok(service.update(id,model));
        } catch (ServiceException e) {
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
