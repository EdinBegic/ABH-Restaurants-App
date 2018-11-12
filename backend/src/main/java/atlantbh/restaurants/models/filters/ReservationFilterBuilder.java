package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class ReservationFilterBuilder extends BaseFilterBuilder<ReservationSortKeys, ReservationFilterBuilder> {

    private Long userId;
    private Date startTime;
    private Date finishTime;
    private Long restaurantId;
    private Boolean confirmed;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if(userId != null) {
            rootCriteria.createAlias("user", "u");
            rootCriteria.add(Restrictions.eq("u.id", userId));
        }
        if(startTime != null) {
            rootCriteria.add(Restrictions.ge("createdAt", startTime));
        }
        if(finishTime != null) {
            rootCriteria.add(Restrictions.le("createdAt", finishTime));
        }
        if(restaurantId != null) {
            rootCriteria.createAlias("restaurantTable", "rt");
            rootCriteria.createAlias("rt.restaurant", "rest");
            rootCriteria.add(Restrictions.eq("rest.id", restaurantId));
        }
        if(confirmed != null) {
            rootCriteria.add(Restrictions.eq("confirmed", confirmed));
        }
        return rootCriteria;
    }

    public ReservationFilterBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public ReservationFilterBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public ReservationFilterBuilder setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public ReservationFilterBuilder setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public ReservationFilterBuilder setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }
}
