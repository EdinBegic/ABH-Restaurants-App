package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.ReviewFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import atlantbh.restaurants.repositories.ReviewRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends BaseService<Review, ReviewSortKeys, ReviewFilterBuilder, ReviewRepository> {

    @Autowired
    RestaurantService restaurantService;

    public Long getNumOfReviewsForRestaurant(Long restaurantId) {
        try {
            return repository.numOfReviewsForRestaurant(restaurantId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Double getRatingForRestaurant(Long restaurantId) {
        try {
            return repository.ratingForRestaurant(restaurantId);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Review create(Review review) throws ServiceException {
        Review createdReview = super.create(review);
        Restaurant restaurant = restaurantService.get(createdReview.getRestaurant().getId());
        restaurant.setReviewSize(restaurant.getReviewSize() + 1);
        restaurant.setAvgRating(getRatingForRestaurant(restaurant.getId()));
        restaurantService.update(restaurant.getId(), restaurant);
        return createdReview;
    }
}
