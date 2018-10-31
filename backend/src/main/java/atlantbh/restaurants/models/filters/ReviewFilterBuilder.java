package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.ReviewSortKeys;
import org.hibernate.Criteria;

public class ReviewFilterBuilder extends BaseFilterBuilder<ReviewSortKeys, ReviewFilterBuilder>{
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return null;
    }
}
