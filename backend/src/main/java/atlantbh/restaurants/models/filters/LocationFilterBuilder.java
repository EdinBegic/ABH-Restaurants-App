package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class LocationFilterBuilder extends BaseFilterBuilder<LocationSortKeys, LocationFilterBuilder> {

    private String city;
    private String country;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if(StringUtils.isNotBlank(city)) {
            rootCriteria.add(Restrictions.ilike("city", city, MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(country)) {
            rootCriteria.add(Restrictions.ilike("country", country, MatchMode.ANYWHERE));
        }

        return rootCriteria;
    }


    public LocationFilterBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public LocationFilterBuilder setCountry(String country) {
        this.country = country;
        return this;
    }
}
