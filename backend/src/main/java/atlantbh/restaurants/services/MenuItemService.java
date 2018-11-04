package atlantbh.restaurants.services;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.models.filters.MenuItemFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuItemSortKeys;
import atlantbh.restaurants.repositories.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService extends BaseService<MenuItem, MenuItemSortKeys, MenuItemFilterBuilder, MenuItemRepository> {
    public List<MenuItem> getByMenu(Long menu) {
        return repository.findByMenu(menu);
    }
}
