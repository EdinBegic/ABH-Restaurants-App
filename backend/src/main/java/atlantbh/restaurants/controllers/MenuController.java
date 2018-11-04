package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController extends BaseController<Menu, MenuService> {

    public ResponseEntity menusByRestuarant(@PathVariable Long restaurant) {
        return ResponseEntity.ok(service.getByRestaurant(restaurant));
    }
}
