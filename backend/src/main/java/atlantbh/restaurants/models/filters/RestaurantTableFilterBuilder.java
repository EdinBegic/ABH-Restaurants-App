package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.RestaurantTableSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class RestaurantTableFilterBuilder extends BaseFilterBuilder<RestaurantTableSortKeys, RestaurantTableFilterBuilder> {

    private Integer sittingPlaces;
    private Long restaurantId;

    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        rootCriteria.createAlias("restaurant", "r");
        if (sittingPlaces != null && sittingPlaces > 0) {
            rootCriteria.add(Restrictions.eq("sittingPlaces", sittingPlaces));
        }
        if (restaurantId != null) {
            rootCriteria.add(Restrictions.eq("r.id", restaurantId));
        }
        return rootCriteria;
    }

    public RestaurantTableFilterBuilder setSittingPlaces(Integer sittingPlaces) {
        this.sittingPlaces = sittingPlaces;
        return this;
    }

    public RestaurantTableFilterBuilder setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }
}
