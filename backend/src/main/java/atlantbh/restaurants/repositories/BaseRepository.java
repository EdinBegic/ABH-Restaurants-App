package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.PaginatedResult;
import atlantbh.restaurants.models.filters.BaseFilterBuilder;

public interface BaseRepository<T, S extends Enum<S>, F extends BaseFilterBuilder<S, F>> {
    void delete(Long id) throws RepositoryException;

    T findById(Long id) throws RepositoryException;

    T save(T entity) throws RepositoryException;

    T update(T entity) throws RepositoryException;

    Long count(F filterBuilder);

    PaginatedResult<T> find(F filterBuilder);
}
