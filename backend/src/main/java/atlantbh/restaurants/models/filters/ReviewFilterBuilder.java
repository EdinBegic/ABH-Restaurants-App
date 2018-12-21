package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ReviewFilterBuilder extends BaseFilterBuilder<ReviewSortKeys, ReviewFilterBuilder> {
    Long restaurantId;
    Long userId;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if(restaurantId != null) {
            rootCriteria.add(Restrictions.eq("restaurant.id", restaurantId));
        }
        if(userId != null) {
            rootCriteria.add(Restrictions.eq("user.id", userId));
        }
        return rootCriteria;
    }

    public ReviewFilterBuilder setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public ReviewFilterBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

}
