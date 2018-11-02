package atlantbh.restaurants.repositories;

import atlantbh.restaurants.exceptions.RepositoryException;
import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.filters.LocationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class LocationRepository extends BaseRepositoryImpl<Location, LocationSortKeys, LocationFilterBuilder> {
    public List<Location> findAllCitiesForCountry(String country) throws RepositoryException {
        try {
            Query query = getSession().createNativeQuery("SELECT *  FROM Location l WHERE l.id IN " +
                    "(SELECT MIN(loc.id) FROM Location loc " +
                    "GROUP BY loc.city) and l.country = :country", Location.class);
            query.setParameter("country", country);
            return query.getResultList();

        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public List<Location> findAllCountries() throws RepositoryException {
        try {
            Query query = getSession()
                    .createNativeQuery("SELECT *  FROM Location l WHERE l.id IN " +
                            "(SELECT MIN(loc.id) FROM Location loc GROUP BY loc.country)", Location.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public Collection<Location> getTopLocations(Integer numOfLocations) throws RepositoryException {
        try {
            // Unfortunately Criteria API doesn't support ordering in subqueries
            Query query = getSession()
                    .createNativeQuery("SELECT * FROM location l order by " +
                            "(SELECT COUNT(*) FROM restaurant r where r.location_id = l.id) desc", Location.class);
            return query.getResultList().subList(0, numOfLocations);

        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
