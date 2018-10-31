package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.CousineSortKeys;
import org.hibernate.Criteria;

public class CousineFilterBuilder extends BaseFilterBuilder<CousineSortKeys, CousineFilterBuilder> {
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        return null;
    }
}
