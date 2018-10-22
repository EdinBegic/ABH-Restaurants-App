package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPasswordHash(String email, String password);

    Boolean existsUserByEmail(String email);
}
