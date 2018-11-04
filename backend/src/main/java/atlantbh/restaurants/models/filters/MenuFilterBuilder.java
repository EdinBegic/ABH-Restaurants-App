package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.MenuSortKeys;
import org.hibernate.Criteria;

public class MenuFilterBuilder extends BaseFilterBuilder<MenuSortKeys, MenuFilterBuilder>{
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return rootCriteria;
    }
}
