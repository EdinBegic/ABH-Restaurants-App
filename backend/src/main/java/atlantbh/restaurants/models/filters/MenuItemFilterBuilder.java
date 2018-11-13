package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.MenuItemSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class MenuItemFilterBuilder extends BaseFilterBuilder<MenuItemSortKeys, MenuItemFilterBuilder> {
    private Long menuId;

    public MenuItemFilterBuilder() {
        this.menuId = null;
    }

    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if (menuId != null) {
            rootCriteria.createAlias("menu", "m");
            rootCriteria.add(Restrictions.eq("m.id", menuId));
        }
        return rootCriteria;
    }

    public MenuItemFilterBuilder setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }
}
