package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCountryAndCity(String country, String city);
}
