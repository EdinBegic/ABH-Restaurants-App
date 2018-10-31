package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.BaseModel;
import atlantbh.restaurants.models.PaginatedResult;
import atlantbh.restaurants.models.filters.BaseFilterBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseRepositoryImpl<T extends BaseModel<T>,S extends Enum<S>,F extends BaseFilterBuilder<S,F>> implements BaseRepository<T,S,F>{

    @PersistenceContext
    protected EntityManager entityManager;

    public PaginatedResult<T> find(F filterBuilder) {
        PaginatedResult<T> paginatedResult = new PaginatedResult<>();
        paginatedResult.setPageNumber(filterBuilder.getPageNumber());
        paginatedResult.setPageSize(filterBuilder.getPageSize());
        paginatedResult.setData(filterBuilder.buildCriteria(getBaseCriteria()).list());
        return paginatedResult;
    }
    public Long count(F filterBuilder) {
        Long result = (Long) filterBuilder.buildCountCriteria(getBaseCriteria()).uniqueResult();
        return result == null ? 0 : result;
    }

    public void delete(Long id) throws RepositoryException {
        try{
            getSession().delete(findById(id));
        }catch (Exception e) {
            throw new RepositoryException("Entity with id " + id + "couldn't be deleted in repository");
        }
    }

    public T findById(Long id) throws RepositoryException {
        try{
            return entityManager.find(getParameterType(), id);
        }catch (Exception e) {
            throw new RepositoryException("Entity with id " + id + "couldn't be found in repository");
        }
    }

    @Transactional
    public T save(T entity) throws RepositoryException {
        try{
            // The created entity is in transient state
            // To save the object first it needs to be placed in persistent state
            entityManager.persist(entity);
            // Force the data in the DB to be persistent
            entityManager.flush();
            return entity;

        }
        catch (Exception e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Transactional
    public T update(T entity) throws RepositoryException {
        try{
            entityManager.merge(entity);
            entityManager.flush();
            return entity;
        } catch (Exception e) {
            throw new RepositoryException("Entity couldn't be updated in repository");
        }
    }

    public List<T> findAll() {
        return getSession().createCriteria(getParameterType()).list();
    }

    // Discarded JpaRepository, now using Hibernate sessions to create,update,delete of mapped entities
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Criteria getBaseCriteria() {
        return getSession().createCriteria(getParameterType());
    }
    // Method to retrieve T entity
    // Solution found on https://stackoverflow.com/a/3403976
    private Class<T> getParameterType() {
        return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
