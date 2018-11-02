package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Cousine;
import atlantbh.restaurants.models.filters.CousineFilterBuilder;
import atlantbh.restaurants.models.sortkeys.CousineSortKeys;
import atlantbh.restaurants.repositories.CousineRepository;
import org.springframework.stereotype.Service;

@Service
public class CousineService extends BaseService<Cousine, CousineSortKeys, CousineFilterBuilder, CousineRepository> {
}
