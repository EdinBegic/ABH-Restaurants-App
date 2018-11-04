package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.models.filters.MenuFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository extends BaseRepositoryImpl<Menu, MenuSortKeys, MenuFilterBuilder> {
    public List<Menu> findByRestaurant(Long restaurant) {
        Criteria criteria = getBaseCriteria();
        criteria.createAlias("restaurant", "restaurant");
        criteria.add(Restrictions.eq("restaurant.id", restaurant));
        return criteria.list();
    }
}
