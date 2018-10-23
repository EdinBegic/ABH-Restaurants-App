package atlantbh.restaurants.services;

import atlantbh.restaurants.controllers.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserRepository> {

    @Autowired
    LocationService locationService;

    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User checkLoginData(String email, String password){
        String passwordHash = hashPassword(password);
        return repo.findByEmailAndPasswordHash(email, passwordHash);
    }

    public Boolean isEmailTaken(String email) {
        return repo.existsUserByEmail(email);
    }
    public User register(@Valid @RequestBody UserRegistrationDTO user) {
        String passwordHash = hashPassword(user.getPassword());
        // Registration of new users is only possible for normal users
        Optional<Location> location = locationService.getById(user.getLocationId());
        User registeredUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), passwordHash, User.RoleName.USER, location.get());
        repo.save(registeredUser);
        return registeredUser;
    }

    private static String hashPassword(String password) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

}
