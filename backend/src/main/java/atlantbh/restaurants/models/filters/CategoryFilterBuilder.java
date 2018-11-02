package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.CategorySortKeys;
import org.hibernate.Criteria;

public class CategoryFilterBuilder extends BaseFilterBuilder<CategorySortKeys, CategoryFilterBuilder> {
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return rootCriteria;
    }
}
