package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Cousine;
import atlantbh.restaurants.models.filters.CousineFilterBuilder;
import atlantbh.restaurants.models.sortkeys.CousineSortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class CousineRepository extends BaseRepositoryImpl<Cousine, CousineSortKeys, CousineFilterBuilder> {
}
