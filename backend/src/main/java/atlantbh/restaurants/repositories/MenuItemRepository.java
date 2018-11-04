package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.models.filters.MenuItemFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuItemSortKeys;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuItemRepository extends BaseRepositoryImpl<MenuItem, MenuItemSortKeys, MenuItemFilterBuilder> {
    public List<MenuItem> findByMenu(Long menu) {
        Criteria criteria = getBaseCriteria();
        criteria.createAlias("menu", "menu");
        criteria.add(Restrictions.eq("menu.id", menu));
        return criteria.list();
    }
}
