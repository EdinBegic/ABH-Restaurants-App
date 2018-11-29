package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ReviewFilterBuilder extends BaseFilterBuilder<ReviewSortKeys, ReviewFilterBuilder> {
    Long id;
    Long userId;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        rootCriteria.add(Restrictions.eq("restaurant.id", id));
        rootCriteria.add(Restrictions.eq("user.id", userId));
        return rootCriteria;
    }

    public ReviewFilterBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReviewFilterBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
