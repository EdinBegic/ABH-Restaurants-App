package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.ReservationSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class ReservationFilterBuilder extends BaseFilterBuilder<ReservationSortKeys, ReservationFilterBuilder> {

    private Long userId;
    private Date startTime;
    private Date finishTime;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if(userId != null) {
            rootCriteria.createAlias("user", "u");
            rootCriteria.add(Restrictions.eq("u.id", userId));
        }
        if(startTime != null) {
            rootCriteria.add(Restrictions.ge("startTime", startTime));
        }
        if(finishTime != null) {
            rootCriteria.add(Restrictions.le("startTime", finishTime));
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
}
