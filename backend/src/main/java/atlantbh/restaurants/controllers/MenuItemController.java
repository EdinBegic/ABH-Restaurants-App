package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.services.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController extends BaseController<MenuItem, MenuItemService> {
    public ResponseEntity itemsByMenu(@PathVariable Long menu) {
        return ResponseEntity.ok(service.getByMenu(menu));
    }

}
