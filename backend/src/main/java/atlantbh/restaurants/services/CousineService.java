package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Cousine;
import atlantbh.restaurants.models.filters.CousineFilterBuilder;
import atlantbh.restaurants.models.sortkeys.CousineSortKeys;
import atlantbh.restaurants.repositories.CousineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CousineService extends BaseService<Cousine, CousineSortKeys, CousineFilterBuilder, CousineRepository> {
    public List<Cousine> findAll() {
        return repository.findAll();
    }
}
