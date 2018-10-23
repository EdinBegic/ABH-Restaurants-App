package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCountryAndCity(String country, String city);

    List<Location> findAllByCountry(String country);

    @Query("SELECT l  FROM Location l WHERE l.id IN (SELECT MIN(loc.id) FROM Location loc GROUP BY loc.country)")
    List<Location> findAllCountires();

    @Query("SELECT l  FROM Location l WHERE l.id IN (SELECT MIN(loc.id) FROM Location loc GROUP BY loc.city) and l.country = :country")
    List<Location> findAllCitiesByCountry(@Param("country") String country);

}
