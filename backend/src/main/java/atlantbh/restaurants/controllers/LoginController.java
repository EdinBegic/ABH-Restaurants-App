package atlantbh.restaurants.controllers;

import atlantbh.restaurants.controllers.dto.LoginRequestDTO;
import atlantbh.restaurants.controllers.dto.LoginResponseDTO;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.services.TokenService;
import atlantbh.restaurants.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController<User, UserService> {

    public @ResponseBody
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            User user = service.checkLoginData(request.getEmail(), request.getPassword());

            if (user != null) {
                user.setPasswordHash(null);
                String token = TokenService.issueToken(true, true);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new LoginResponseDTO(user, token));
            } else {
                throw new ServiceException("");
            }

        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponseDTO("Incorrect email or password!"));
        }
    }


}
