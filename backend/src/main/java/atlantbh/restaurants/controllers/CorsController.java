package atlantbh.restaurants.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsController {

    @RequestMapping(path = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity resolveOptionsRequests() {
        return ResponseEntity.ok(true);
    }

}
