package atlantbh.restaurants.services;

import atlantbh.restaurants.models.RestaurantTable;
import atlantbh.restaurants.models.filters.RestaurantTableFilterBuilder;
import atlantbh.restaurants.models.sortkeys.RestaurantTableSortKeys;
import atlantbh.restaurants.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTableService extends BaseService<RestaurantTable, RestaurantTableSortKeys, RestaurantTableFilterBuilder, RestaurantTableRepository> {

}
