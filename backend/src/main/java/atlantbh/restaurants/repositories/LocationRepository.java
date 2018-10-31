package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.filters.LocationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public  class LocationRepository extends BaseRepositoryImpl<Location,LocationSortKeys, LocationFilterBuilder> {
}
