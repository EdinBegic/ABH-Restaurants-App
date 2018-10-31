package atlantbh.restaurants.services;

import atlantbh.restaurants.exceptions.PropertyReservedException;
import atlantbh.restaurants.exceptions.StringMissmatchException;
import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.Role;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.dto.LoginRequestDTO;
import atlantbh.restaurants.models.dto.LoginResponseDTO;
import atlantbh.restaurants.models.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import atlantbh.restaurants.repositories.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Service
public class UserService extends BaseService<User, UserSortKeys, UserFilterBuilder, UserRepository> {

    @Autowired
    LocationService locationService;

    private static String hashPassword(String password) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

    public Collection<User> all() {
        return repository.findAll();
    }

    public Long count(UserFilterBuilder ufb) {
        return repository.count(ufb);
    }

    public LoginResponseDTO checkLoginData(LoginRequestDTO request) {
        String passwordHash = hashPassword(request.getPassword());
        Criteria criteria = repository.getBaseCriteria();
        criteria.add(Restrictions.eq("email", request.getEmail()));
        criteria.add(Restrictions.eq("passwordHash", passwordHash));

        List<User> users = criteria.list();
        if (users.size() > 1)
            throw new ServiceException("There are multiple users with the same email and password");
        if (users.size() == 0) {
            throw new ServiceException("Email or password is invalid");
        }
        User user = users.get(0);
        user.setPasswordHash(null);
        String token = TokenService.issueToken(true, true);
        return new LoginResponseDTO(user, token);
    }

    public Boolean isEmailTaken(String email) {
        Criteria criteria = repository.getBaseCriteria();
        criteria.add(Restrictions.eq("email", email));
        if (criteria.list().size() > 0)
            return true;
        return false;
    }

    public User get(@Valid @RequestBody UserRegistrationDTO user) throws ServiceException, PropertyReservedException, StringMissmatchException {
        if (isEmailTaken(user.getEmail())) {
            throw new PropertyReservedException("Email is already in use");
        }
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            throw new StringMissmatchException("Passwords don't match");
        }
        String passwordHash = hashPassword(user.getPassword());
        // Registration of new users is only possible for normal users
        try {
            Location location = locationService.get(user.getLocationId());
            User registeredUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), passwordHash, Role.USER, location);
            create(registeredUser);
            return registeredUser;
        } catch (ServiceException e) {
            throw e;
        }
    }

}
