package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.models.filters.MenuItemFilterBuilder;
import atlantbh.restaurants.services.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController extends BaseController<MenuItem, MenuItemService> {
    public ResponseEntity filter(@RequestParam Long menuId) {
        MenuItemFilterBuilder mifb = new MenuItemFilterBuilder()
                .setMenuId(menuId);
        return ResponseEntity.ok(service.filter(mifb).getData());
    }

}
