package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepositoryImpl<User, UserSortKeys, UserFilterBuilder> {

    public User checkLoginData(String email, String passwordHash) throws RepositoryException {
        try{
            Criteria criteria = getBaseCriteria();
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("passwordHash", passwordHash));
            return (User) criteria.uniqueResult();
        } catch (Exception e) {
            throw new RepositoryException("Email or password is invalid",e);
        }
    }
}
