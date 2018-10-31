package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.models.sortkeys.RestaurantSortKeys;
import atlantbh.restaurants.models.filters.RestaurantFilterBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository extends BaseRepositoryImpl<Restaurant,RestaurantSortKeys,RestaurantFilterBuilder>{
}
