package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.services.RestaurantService;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {

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
                                 @RequestParam(value = "location", required = false) String location) {
        try {
            RestaurantFilterBuilder rfb = new RestaurantFilterBuilder()
                    .setPriceRange(priceRange)
                    .setAvgRating(avgRating)
                    .setQuery(query)
                    .setName(name)
                    .setCategory(category)
                    .setCousines(cousines)
                    .setLocation(location)
                    .setPageSize(pageSize)
                    .setPageNumber(pageNumber)
                    .setSortAsc(sortAsc)
                    .setSortKey(EnumUtils.getEnum(RestaurantSortKeys.class, sortKey));
            return ResponseEntity.ok(service.filter(rfb));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity popularRestaurants(@RequestParam Integer size) {

        return ResponseEntity.ok(service.getPopularRestaurants(size));
    }
}
