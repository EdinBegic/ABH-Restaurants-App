package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.OrderBySqlFormula;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class RestaurantFilterBuilder extends BaseFilterBuilder<RestaurantSortKeys, RestaurantFilterBuilder> {

    private String query;
    private String name;
    private Integer priceRange;
    private String location;
    private String category;
    private List<String> cousines;
    private Double avgRating;
    private Double latitude;
    private Double longitude;
    private Long locationId;
    public RestaurantFilterBuilder() {
        query = null;
        name = null;
        priceRange = null;
        location = null;
        category = null;
        cousines = null;
        avgRating = null;
    }

    @Override
    public Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {

        rootCriteria.createAlias("category", "cat");
        rootCriteria.createAlias("cousine", "cous");
        rootCriteria.createAlias("location", "loc");
        if (StringUtils.isNotBlank(query)) {
            Criterion criterion = Restrictions.or(Restrictions.ilike("name", query, MatchMode.ANYWHERE),
                    Restrictions.ilike("cat.name", query, MatchMode.ANYWHERE),
                    Restrictions.ilike("cous.name", query, MatchMode.ANYWHERE),
                    Restrictions.or(Restrictions.ilike("loc.city", query, MatchMode.ANYWHERE),
                            Restrictions.ilike("loc.country", query, MatchMode.ANYWHERE)));
            rootCriteria.add(criterion);
        }
        if (StringUtils.isNotBlank(name)) {
            rootCriteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(category)) {
            rootCriteria.add(Restrictions.ilike("cat.name", category));
        }
        if (priceRange != null && priceRange > 0 && priceRange < 5) {
            rootCriteria.add(Restrictions.eq("priceRange", priceRange));
        }
        if (StringUtils.isNotBlank(location)) {
            Criterion criterion = Restrictions.or(Restrictions.ilike("loc.city", location, MatchMode.ANYWHERE),
                    Restrictions.ilike("loc.country", location, MatchMode.ANYWHERE));
            rootCriteria.add(criterion);
        }
        if (!CollectionUtils.isEmpty(cousines)) {
            rootCriteria.add(Restrictions.in("cous.name", cousines));
        }
        if (avgRating != null && avgRating >= 0 && avgRating <= 5) {
            rootCriteria.add(Restrictions.eq("avgRating", avgRating));
        }
        if (locationId != null) {
            rootCriteria.add(Restrictions.eq("loc.id", locationId));
        }

        return rootCriteria;
    }

    @Override
    public void addOrderBy(Criteria rootCriteria) {
        if (this.getSortKey() != null && this.getSortAsc() != null) {
            GeometryFactory gf;
            Point point = null;
            String customOrder = null;
            if (this.getSortKey().toString() == "nearest") {
                gf = new GeometryFactory();
                point = gf.createPoint(new Coordinate(latitude, longitude, 0.0));
                customOrder = "ST_Distance(coordinates, ST_Geomfromtext('POINT(" + latitude + " " + longitude + ")')) ";
            }
            if (this.getSortAsc()) {
                if (this.getSortKey().toString() == "nearest") {
                    customOrder += "ASC";
                    rootCriteria.addOrder(OrderBySqlFormula.sqlFormula(customOrder));
                } else {
                    rootCriteria.addOrder(Order.asc(this.getSortKey().toString()));
                }
            } else {
                if (this.getSortKey().toString() == "nearest") {
                    customOrder += "DESC";
                    rootCriteria.addOrder(OrderBySqlFormula.sqlFormula(customOrder));
                } else {
                    rootCriteria.addOrder(Order.desc(this.getSortKey().toString()).nulls(NullPrecedence.LAST));
                }
            }
        }
        if (this.isUseDefaultSort()) {
            rootCriteria.addOrder(Order.desc("id"));
        }

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

    public RestaurantFilterBuilder setCousines(List<String> cousines) {
        this.cousines = cousines;
        return this;
    }

    public RestaurantFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantFilterBuilder setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    public RestaurantFilterBuilder setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public RestaurantFilterBuilder setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public RestaurantFilterBuilder setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }
}
