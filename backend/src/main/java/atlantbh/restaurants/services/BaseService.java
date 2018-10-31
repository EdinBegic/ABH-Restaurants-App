package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.BaseModel;
import atlantbh.restaurants.models.PaginatedResult;
import atlantbh.restaurants.models.filters.BaseFilterBuilder;
import atlantbh.restaurants.repositories.BaseRepositoryImpl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<M extends BaseModel<M>, S extends Enum<S>, F extends BaseFilterBuilder<S, F>, R extends BaseRepositoryImpl<M, S, F>> {

    @Autowired
    protected R repository;

    public PaginatedResult<M> filter(F filterBuilder) {
        return repository.find(filterBuilder);
    }

    public M findById(Long id) {
        return repository.findById(id);
    }

    public M get(Long id) {
        M model = repository.findById(id);
        if (model != null) {
            return model;
        }
        throw new ServiceException("Model with id: " + id + " not found");
    }

    public M create(M m) throws ServiceException {
        try {
            repository.save(m);
            return m;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public M update(Long id, M data) throws ServiceException {
        try {
            M model = get(id);
            if (model == null) {
                throw new ServiceException("Requested model not found");
            }
            model.update(data);
            repository.update(model);
            return model;
        } catch (RepositoryException e) {
            throw new ServiceException("Requested model couldn't be updated");
        }
    }

    public void delete(Long id) throws ServiceException {
        try {
            M model = get(id);
            if (model == null) {
                throw new ServiceException("Requested model not found");
            }
            repository.delete(id);
        } catch (ServiceException e) {
            throw new ServiceException("Requested model couldn't be deleted");
        }
    }
}
