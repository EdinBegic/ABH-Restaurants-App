package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.models.filters.MenuFilterBuilder;
import atlantbh.restaurants.models.sortkeys.MenuSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository extends BaseRepositoryImpl<Menu, MenuSortKeys, MenuFilterBuilder> {
}
