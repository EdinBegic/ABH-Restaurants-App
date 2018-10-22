package atlantbh.restaurants.controllers;

import atlantbh.restaurants.controllers.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.DefaultError;
import atlantbh.restaurants.models.ErrorResponseWrapper;
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

    public ResponseEntity registerUser(@Valid @RequestBody UserRegistrationDTO user, BindingResult bindingResult) {
        if(service.isEmailTaken(user.getEmail()) == true) {
            DefaultError error = new DefaultError("email", "Email is already in use");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseWrapper(error));
        }
        if(!user.getPassword().equals(user.getConfirmedPassword())) {
            DefaultError error = new DefaultError("password", "Passwords don't match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseWrapper(error));
        }
        if (bindingResult.hasErrors()) {
            DefaultError error = new DefaultError();
            error.setField(bindingResult.getFieldError().getField());
            error.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseWrapper(error));
        }
        User registeredUser = service.register(user);
        return ResponseEntity.ok(registeredUser);
    }
}
