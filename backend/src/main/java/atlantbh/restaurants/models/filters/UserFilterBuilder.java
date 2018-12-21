package atlantbh.restaurants.models.filters;

import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class UserFilterBuilder extends BaseFilterBuilder<UserSortKeys, UserFilterBuilder> {

    private String firstName;
    private String email;
    @Override
    protected Criteria addConditions(Criteria rootCriteria, boolean isCountCriteria) {
        if(StringUtils.isNotBlank(firstName)) {
            rootCriteria.add(Restrictions.ilike("firstName", firstName, MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(email)) {
            rootCriteria.add(Restrictions.ilike("email", email, MatchMode.ANYWHERE));
        }

        return rootCriteria;
    }

    public UserFilterBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserFilterBuilder setEmail(String email) {
        this.email = email;
        return this;
    }
}
