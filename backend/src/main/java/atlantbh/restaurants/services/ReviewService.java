package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.ReviewFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import atlantbh.restaurants.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends BaseService<Review, ReviewSortKeys, ReviewFilterBuilder, ReviewRepository>{
}
