package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.RestaurantTable;
import atlantbh.restaurants.models.filters.RestaurantTableFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantTableSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantTableRepository extends BaseRepositoryImpl<RestaurantTable, RestaurantTableSortKeys, RestaurantTableFilterBuilder> {

}
