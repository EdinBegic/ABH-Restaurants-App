package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController extends BaseController<User, UserService> {

    public ResponseEntity registerUser(@Valid @RequestBody UserRegistrationDTO user, BindingResult bindingResult) throws Exception {
        try {
            User registeredUser = service.get(user, bindingResult);
            return ResponseEntity.ok(registeredUser);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
