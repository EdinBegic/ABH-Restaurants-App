package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepositoryImpl<User, UserSortKeys, UserFilterBuilder> {

}
