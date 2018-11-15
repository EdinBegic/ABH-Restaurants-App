package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Cousine;
import atlantbh.restaurants.models.filters.CousineFilterBuilder;
import atlantbh.restaurants.services.CousineService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CousineController extends BaseController<Cousine, CousineService> {

    public ResponseEntity filter() {
        try {
            //for now no specific filter
            CousineFilterBuilder cfb = new CousineFilterBuilder();
            return ResponseEntity.ok(service.filter(cfb).getData());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
