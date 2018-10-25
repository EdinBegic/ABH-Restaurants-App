package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
