package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
