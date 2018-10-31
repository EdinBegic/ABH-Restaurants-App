package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.ReviewFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends BaseRepositoryImpl<Review, ReviewSortKeys, ReviewFilterBuilder> {
}
