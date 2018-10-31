package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {

    public ResponseEntity filter(@RequestParam(value = "q", required = false) String query,
                                 @RequestParam(value = "priceRange", required = false) Integer priceRange,
                                 @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                 @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
                                 @RequestParam(value = "sortKey", required = false) String sortKey,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "category",required = false) String category,
                                 @RequestParam(value = "cousine", required = false) String cousine,
                                 @RequestParam(value = "location", required = false) String location){
        RestaurantFilterBuilder rfb = new RestaurantFilterBuilder().setPriceRange(priceRange)
                                                                    .setQuery(query)
                                                                    .setName(name)
                                                                    .setCategory(category)
                                                                    .setCousine(cousine)
                                                                    .setLocation(location)
                                                                    .setPageSize(pageSize)
                                                                    .setPageNumber(pageNumber);
        if(sortKey != null)
            rfb = rfb.setSortKey(RestaurantSortKeys.valueOf(sortKey));
        return ResponseEntity.ok(service.filter(rfb));

    }
    public ResponseEntity popularRestaurants(@RequestParam Integer size) {

        return ResponseEntity.ok(service.getPopularRestaurants(size));
    }
    public ResponseEntity reviewSize(@PathVariable Long id){
        try{
            return ResponseEntity.ok(service.getReviewSize(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity avgRating(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(service.getAvgRating(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
