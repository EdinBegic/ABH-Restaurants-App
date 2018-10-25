package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Role;
import atlantbh.restaurants.models.dto.LoginRequestDTO;
import atlantbh.restaurants.models.dto.LoginResponseDTO;
import atlantbh.restaurants.models.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.repositories.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserRepository> {

    @Autowired
    LocationService locationService;

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public LoginResponseDTO checkLoginData(LoginRequestDTO request){
        String passwordHash = hashPassword(request.getPassword());
        User user = repository.findByEmailAndPasswordHash(request.getEmail(), passwordHash);
        if (user == null) {
            throw new ServiceException("Email or password is invalid");
        }
        user.setPasswordHash(null);
        String token = TokenService.issueToken(true, true);
        return new LoginResponseDTO(user,token);
    }

    public Boolean isEmailTaken(String email) {
        return repository.existsUserByEmail(email);
    }
    public User get(@Valid @RequestBody UserRegistrationDTO user, BindingResult bindingResult) throws Exception {
        if(this.isEmailTaken(user.getEmail())) {
            throw  new Exception("Email is already in use");
        }
        if(!user.getPassword().equals(user.getConfirmedPassword())) {
            throw new Exception("Passwords don't match");
        }
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        String passwordHash = hashPassword(user.getPassword());
        // Registration of new users is only possible for normal users
        Optional<Location> location = locationService.getById(user.getLocationId());
        if(location.get() == null)
            throw new Exception("Location not found");
        User registeredUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), passwordHash, Role.USER, location.get());
        repository.save(registeredUser);
        return registeredUser;
    }

    private static String hashPassword(String password) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

}
