package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Review;
import atlantbh.restaurants.models.filters.ReviewFilterBuilder;
import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends BaseRepositoryImpl<Review, ReviewSortKeys, ReviewFilterBuilder> {
    public Long numOfReviewsForRestaurant(Long restaurantId) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.createAlias("restaurant", "r");
            return (Long) criteria.add(Restrictions.eq("r.id", restaurantId))
                    .setProjection(Projections.rowCount())
                    .uniqueResult();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public Double ratingForRestaurant(Long restaurantId) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.createAlias("restaurant", "r");
            return (Double) criteria.add(Restrictions.eq("r.id", restaurantId))
                    .setProjection(Projections.avg("mark"))
                    .uniqueResult();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
