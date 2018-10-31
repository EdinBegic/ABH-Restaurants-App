package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.filters.LocationFilterBuilder;
import atlantbh.restaurants.models.sortkeys.LocationSortKeys;
import atlantbh.restaurants.repositories.LocationRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationService extends BaseService<Location, LocationSortKeys, LocationFilterBuilder, LocationRepository> {

    // TODO Change method to use Criteria instead of native query
    public List<Location> findAllCitiesForCountry(String country) {
        Query query = repository.getSession().createNativeQuery("SELECT *  FROM Location l WHERE l.id IN " +
                                                                    "(SELECT MIN(loc.id) FROM Location loc " +
                                                                    "GROUP BY loc.city) and l.country = :country", Location.class);
        query.setParameter("country", country);
        return query.getResultList();

    }
    // TODO Change method to use Criteria instead of native query
    public List<Location> findAllCountires() {
       Query query = repository.getSession().createNativeQuery("SELECT *  FROM Location l WHERE l.id IN (SELECT MIN(loc.id) FROM Location loc GROUP BY loc.country)", Location.class);
        return query.getResultList();
    }

    public Collection<Location> getTopLocations(Integer numOfLocations) throws Exception {

        // outdated method
        // TODO retrieving top locations with count criteria
        ArrayList<Location> locations = new ArrayList<>(repository.findAll());
        locations.removeIf(loc -> loc.getRestaurants().size() == 0 );
        Collections.sort(locations, (o1, o2) -> o1.getRestaurants().size() > o2.getRestaurants().size() ? 1 : 0);
        if(numOfLocations > locations.size())
            throw new Exception("Number of requested locations exceeds number of saved locations");
        return locations.subList(0,numOfLocations);
    }


}
