package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.MenuItemSortKeys;
import org.hibernate.Criteria;

public class MenuItemFilterBuilder extends BaseFilterBuilder<MenuItemSortKeys, MenuItemFilterBuilder> {
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return rootCriteria;
    }
}
