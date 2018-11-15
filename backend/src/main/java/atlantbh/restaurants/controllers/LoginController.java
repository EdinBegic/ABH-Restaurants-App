package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.dto.LoginRequestDTO;
import atlantbh.restaurants.models.dto.LoginResponseDTO;
import atlantbh.restaurants.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController extends BaseController<User, UserService> {

    public @ResponseBody
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.checkLoginData(request));

        } catch (ServiceException e) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponseDTO("Incorrect email or password!"));
        }
    }


}
