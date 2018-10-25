package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.Restaurant;
import atlantbh.restaurants.services.RestaurantService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {
    
}
