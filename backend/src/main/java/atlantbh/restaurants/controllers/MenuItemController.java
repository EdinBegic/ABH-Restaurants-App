package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.MenuItem;
import atlantbh.restaurants.models.filters.MenuItemFilterBuilder;
import atlantbh.restaurants.services.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController extends BaseController<MenuItem, MenuItemService> {
    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "menuId", required = false) Long menuId,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNumber") Integer pageNumber) {
        MenuItemFilterBuilder mifb = new MenuItemFilterBuilder()
                .setMenuId(menuId)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);
        return ResponseEntity.ok(service.filter(mifb).getData());
    }

}
