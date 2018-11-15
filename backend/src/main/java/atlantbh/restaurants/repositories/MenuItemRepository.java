package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.models.filters.MenuItemFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuItemSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class MenuItemRepository extends BaseRepositoryImpl<MenuItem, MenuItemSortKeys, MenuItemFilterBuilder> {
}
