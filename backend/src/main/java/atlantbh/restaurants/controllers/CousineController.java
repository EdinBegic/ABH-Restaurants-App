package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Cousine;
import atlantbh.restaurants.models.filters.CousineFilterBuilder;
import atlantbh.restaurants.services.CousineService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cousines")
public class CousineController extends BaseController<Cousine, CousineService> {

    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNumber") Integer pageNumber) {
        try {
            //for now no specific filter
            CousineFilterBuilder cfb = new CousineFilterBuilder()
                    .setPageNumber(pageNumber)
                    .setPageSize(pageSize);
            return ResponseEntity.ok(service.filter(cfb).getData());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
