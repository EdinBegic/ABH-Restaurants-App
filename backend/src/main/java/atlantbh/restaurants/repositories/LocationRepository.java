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
            // Result set is mapped to Location class with an aditional field for the number of restaurants
            // Mapping is defined on top of the entity
            Query query = getSession()
                    .createNativeQuery("SELECT l.*, (SELECT COUNT(*) FROM restaurant r where r.location_id = l.id) as numOfRestaurants" +
                            "  FROM location l order by numOfRestaurants desc", "TopLocationMapping");
            return query.getResultList().subList(0, numOfLocations);

        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
