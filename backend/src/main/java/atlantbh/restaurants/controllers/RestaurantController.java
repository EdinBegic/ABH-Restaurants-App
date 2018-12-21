package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.services.RestaurantService;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {

    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "q", required = false) String query,
                                 @RequestParam(value = "priceRange", required = false) Integer priceRange,
                                 @RequestParam(value = "avgRating", required = false) Double avgRating,
                                 @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                 @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
                                 @RequestParam(value = "sortKey", required = false) String sortKey,
                                 @RequestParam(value = "sortAsc", required = false) Boolean sortAsc,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "categories", required = false) String category,
                                 @RequestParam(value = "cousines", required = false) List<String> cousines,
                                 @RequestParam(value = "location", required = false) String location,
                                 @RequestParam(value = "latitude", required = false) Double latitude,
                                 @RequestParam(value = "longitude", required = false) Double longitude) {
        try {
            RestaurantFilterBuilder rfb = new RestaurantFilterBuilder()
                    .setPriceRange(priceRange)
                    .setAvgRating(avgRating)
                    .setQuery(query)
                    .setName(name)
                    .setCategory(category)
                    .setCousines(cousines)
                    .setLocation(location)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .setPageSize(pageSize)
                    .setPageNumber(pageNumber)
                    .setSortAsc(sortAsc)
                    .setSortKey(EnumUtils.getEnum(RestaurantSortKeys.class, sortKey));
            return ResponseEntity.ok(service.filter(rfb));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/popular")
    public ResponseEntity popularRestaurants(@RequestParam Integer size) {

        return ResponseEntity.ok(service.getPopularRestaurants(size));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @PostMapping
    @Override
    public ResponseEntity create(@RequestBody Restaurant model) {
        return super.create(model);
    }

    @GetMapping("/nearest")
    public ResponseEntity getNearestRestaurants(@RequestParam Double latitude,
                                                @RequestParam Double longitude) {
        try {
            return ResponseEntity.ok(service.getNearestRestaurants(latitude, longitude));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/coordinates")
    public ResponseEntity getCoordinates(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.getCoordinates(id));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity update(@PathVariable Long id, @RequestBody Restaurant model) {
        try {
            return ResponseEntity.ok(service.update(id,model));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
