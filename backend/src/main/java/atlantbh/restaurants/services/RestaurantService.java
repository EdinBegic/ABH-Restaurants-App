package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.repositories.RestaurantRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@Service
public class RestaurantService extends BaseService<Restaurant, RestaurantSortKeys, RestaurantFilterBuilder, RestaurantRepository> {

    public Collection<Restaurant> getPopularRestaurants(Integer numOfRestaurants) throws ServiceException {

        try {
            Criteria criteria = repository.getBaseCriteria();
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RANDOM()"));
            criteria.setMaxResults(numOfRestaurants);
            return criteria.list();
        } catch (Exception e) {
            throw new ServiceException("Couldn't retrive popular restaurants", e);
        }

    }

    @Transient
    public Integer getReviewSize(Long id) throws ServiceException{
        try{
            return  get(id).getReviews().size();
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Transient
    public BigDecimal getAvgRating(Long id) throws ServiceException{
        try {
            Collection<Review> reviews = get(id).getReviews();
            Double sumRating = Double.valueOf(0);
            for(Review review: reviews) {
                sumRating+=review.getMark().doubleValue();
            }
            if(sumRating.equals(Double.valueOf(0))) {
                return new BigDecimal(0);
            }
            return new BigDecimal(sumRating).divide(new BigDecimal(reviews.size()),2, RoundingMode.HALF_UP);
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }
}
