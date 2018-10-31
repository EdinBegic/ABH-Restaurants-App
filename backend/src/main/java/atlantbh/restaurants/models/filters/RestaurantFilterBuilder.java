package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
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

        if (query != null && query.trim().length() > 0) { // ignore whtiespace characters
            Criterion criterion = Restrictions.or(Restrictions.ilike("name", query,MatchMode.ANYWHERE),
                                                    Restrictions.ilike("category.name", query, MatchMode.ANYWHERE),
                                                    Restrictions.ilike("cousine.name", query, MatchMode.ANYWHERE),
                                                    Restrictions.or(Restrictions.ilike("location.city",query, MatchMode.ANYWHERE),
                                                                    Restrictions.ilike("location.country",query, MatchMode.ANYWHERE)));
            rootCriteria.createAlias("category","category");
            rootCriteria.createAlias("cousine","cousine");
            rootCriteria.createAlias("location","location");
            rootCriteria.add(criterion);
        }
        if(name != null && query.trim().length() > 0) {
            rootCriteria.add(Restrictions.ilike("name",name, MatchMode.ANYWHERE));
        }
        if (category != null && category.trim().length() > 0) {
            rootCriteria.createAlias("category","category");
            rootCriteria.add(Restrictions.ilike("category.name", category,MatchMode.ANYWHERE));
        }
        if (priceRange != null && priceRange > 0) {
            rootCriteria.add(Restrictions.eq("priceRange", priceRange));
        }
        if (location != null && location.trim().length() > 0) {
            rootCriteria.createAlias("location", "location");
            Criterion criterion = Restrictions.or(Restrictions.ilike("location.city", location, MatchMode.ANYWHERE),
                    Restrictions.ilike("location.country", location,MatchMode.ANYWHERE));
            rootCriteria.add(criterion);
        }
        if (cousine != null && cousine.trim().length() > 0) {
            rootCriteria.createAlias("cousine","cousine");
            rootCriteria.add(Restrictions.ilike("cousine.name", cousine,MatchMode.ANYWHERE));
        }
        return rootCriteria;
    }

    public String getQuery() {
        return query;
    }

    public RestaurantFilterBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public RestaurantFilterBuilder setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public RestaurantFilterBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RestaurantFilterBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCousine() {
        return cousine;
    }

    public RestaurantFilterBuilder setCousine(String cousine) {
        this.cousine = cousine;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }
}
