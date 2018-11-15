package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.models.filters.MenuFilterBuilder;
import atlantbh.restaurants.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController extends BaseController<Menu, MenuService> {

    public ResponseEntity filter(@RequestParam Long restaurantId,
                                 @RequestParam Integer pageSize,
                                 @RequestParam Integer pageNumber) {

        MenuFilterBuilder mfb = new MenuFilterBuilder()
                .setRestaurantId(restaurantId)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);
        return ResponseEntity.ok(service.filter(mfb).getData());
    }
}
