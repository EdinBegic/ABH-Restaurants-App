package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import org.hibernate.Criteria;

public class LocationFilterBuilder extends BaseFilterBuilder<LocationSortKeys, LocationFilterBuilder> {
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return rootCriteria;
    }
}
