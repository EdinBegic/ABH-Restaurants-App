package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.models.filters.MenuFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuSortKeys;
import atlantbh.restaurants.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService extends  BaseService<Menu, MenuSortKeys, MenuFilterBuilder, MenuRepository> {
}
