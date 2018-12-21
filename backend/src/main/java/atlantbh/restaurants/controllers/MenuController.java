package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Menu;
import atlantbh.restaurants.models.filters.MenuFilterBuilder;
import atlantbh.restaurants.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
public class MenuController extends BaseController<Menu, MenuService> {

    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "restaurantId", required = false) Long restaurantId,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNumber") Integer pageNumber) {

        MenuFilterBuilder mfb = new MenuFilterBuilder()
                .setRestaurantId(restaurantId)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);
        return ResponseEntity.ok(service.filter(mfb).getData());
    }
}
