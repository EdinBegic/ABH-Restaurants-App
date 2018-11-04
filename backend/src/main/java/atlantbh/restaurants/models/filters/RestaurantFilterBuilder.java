package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class RestaurantFilterBuilder extends BaseFilterBuilder<RestaurantSortKeys, RestaurantFilterBuilder> {

    private String query;
    private String name;
    private Integer priceRange;
    private String location;
    private String category;
    private String cousine;
    //TODO implement filtering for average rating of restaurant

    public RestaurantFilterBuilder() {
        query = null;
        name = null;
        priceRange = null;
        location = null;
        category = null;
        cousine = null;
    }

    @Override
    public Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {

        if (StringUtils.isNotBlank(query)) {
            Criterion criterion = Restrictions.or(Restrictions.ilike("name", query, MatchMode.ANYWHERE),
                    Restrictions.ilike("cat.name", query, MatchMode.ANYWHERE),
                    Restrictions.ilike("cous.name", query, MatchMode.ANYWHERE),
                    Restrictions.or(Restrictions.ilike("loc.city", query, MatchMode.ANYWHERE),
                            Restrictions.ilike("loc.country", query, MatchMode.ANYWHERE)));
            rootCriteria.createAlias("category", "cat");
            rootCriteria.createAlias("cousine", "cous");
            rootCriteria.createAlias("location", "loc");
            rootCriteria.add(criterion);
        } else { // otherwise creating aliases would throw exception
            if (StringUtils.isNotBlank(name)) {
                rootCriteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotBlank(category)) {
                rootCriteria.createAlias("category", "cat");
                rootCriteria.add(Restrictions.ilike("cat.name", category, MatchMode.ANYWHERE));
            }
            if (priceRange != null && priceRange > 0) {
                rootCriteria.add(Restrictions.eq("priceRange", priceRange));
            }
            if (StringUtils.isNotBlank(location)) {
                rootCriteria.createAlias("location", "loc");
                Criterion criterion = Restrictions.or(Restrictions.ilike("loc.city", location, MatchMode.ANYWHERE),
                        Restrictions.ilike("loc.country", location, MatchMode.ANYWHERE));
                rootCriteria.add(criterion);
            }
            if (StringUtils.isNotBlank(cousine)) {
                rootCriteria.createAlias("cousine", "c");
                rootCriteria.add(Restrictions.ilike("c.name", cousine, MatchMode.ANYWHERE));
            }
        }
        return rootCriteria;
    }

    public RestaurantFilterBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    public RestaurantFilterBuilder setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    public RestaurantFilterBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public RestaurantFilterBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public RestaurantFilterBuilder setCousine(String cousine) {
        this.cousine = cousine;
        return this;
    }

    public RestaurantFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }
}
