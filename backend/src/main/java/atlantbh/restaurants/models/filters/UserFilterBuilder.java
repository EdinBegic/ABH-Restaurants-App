package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import org.hibernate.Criteria;

public class UserFilterBuilder extends BaseFilterBuilder<UserSortKeys, UserFilterBuilder> {
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return rootCriteria;
    }
}
