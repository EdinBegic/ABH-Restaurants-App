package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.ReviewFilterBuilder;
import atlantbh.restaurants.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends BaseController<Review, ReviewService> {
    @GetMapping("restaurant/{id}/size")
    public ResponseEntity numOfReviewsForRestaurant(@PathVariable(name = "id") Long restauarantId) {
        try {
            return ResponseEntity.ok(service.getNumOfReviewsForRestaurant(restauarantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/restaurant/{id}/rating")
    public ResponseEntity ratingForRestaurant(@PathVariable(name = "id") Long restaurantId) {
        try {
            return ResponseEntity.ok(service.getRatingForRestaurant(restaurantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @Override
    public ResponseEntity create(@RequestBody Review model) {
        return super.create(model);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @GetMapping("/restaurant/{id}/user/{userId}")
    public ResponseEntity hasReviewedRestaurant(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        ReviewFilterBuilder rfb = new ReviewFilterBuilder()
                .setId(id)
                .setUserId(userId);
        return ResponseEntity.ok(service.hasReviewed(rfb));
    }

}
