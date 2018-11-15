package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.MenuSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class MenuFilterBuilder extends BaseFilterBuilder<MenuSortKeys, MenuFilterBuilder> {

    private Long restaurantId;

    public MenuFilterBuilder() {
        restaurantId = null;
    }

    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if (restaurantId != null) {
            rootCriteria.add(Restrictions.eq("restaurant.id", restaurantId));
        }
        return rootCriteria;
    }

    public MenuFilterBuilder setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }
}
