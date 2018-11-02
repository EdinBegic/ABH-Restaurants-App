package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController extends BaseController<Review, ReviewService> {
    public ResponseEntity numOfReviewsForRestaurant(@PathVariable(name = "id") Long restauarantId) {
        try {
            return ResponseEntity.ok(service.getNumOfReviewsForRestaurant(restauarantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity ratingForRestaurant(@PathVariable(name = "id") Long restaurantId) {
        try {
            return ResponseEntity.ok(service.getRatingForRestaurant(restaurantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
