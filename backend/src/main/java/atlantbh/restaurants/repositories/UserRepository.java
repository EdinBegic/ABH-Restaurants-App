package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepositoryImpl<User, UserSortKeys, UserFilterBuilder> {

    public User checkLoginData(String email, String passwordHash) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("passwordHash", passwordHash));
            return (User) criteria.uniqueResult();
        } catch (Exception e) {
            throw new RepositoryException("Email or password is invalid", e);
        }
    }

    public boolean isEmailTaken(String email) throws RepositoryException {
        try {
            Criteria criteria = getBaseCriteria();
            criteria.add(Restrictions.eq("email", email));
            if (criteria.list().size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RepositoryException("Error occurred while checking if email is unique", e);
        }
    }
}
