package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.services.ReviewService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController extends BaseController<Review, ReviewService> {
}
