package atlantbh.restaurants;

import atlantbh.restaurants.models.*;
import atlantbh.restaurants.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbLoader implements CommandLineRunner {
    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CousineService cousineService;

    @Override
    public void run(String... args) throws Exception {
        addLocations();
        addUsers();
        addCategories();
        addCousines();
        addRestaurants();
    }

    private void addUsers() {
        Location loc = locationService.getById((long) 2).get();
        userService.save(new User("Admin", "Admin", "admin@abh.com", "123-456-789", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.ADMIN, loc));
        loc = locationService.getById((long) 3).get();
        userService.save(new User("Edin", "Begic", "edinbegic@abh.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
    }

    private void addLocations() {
        locationService.save(new Location("Bosnia", "Sarajevo"));
        locationService.save(new Location("Bosnia", "Tuzla"));
        locationService.save(new Location("Serbia", "Belgrade"));
        locationService.save(new Location("Croatia", "Zagreb"));
        locationService.save(new Location("Germany", "Berlin"));
        locationService.save(new Location("United Kingdom", "London"));
        locationService.save(new Location("Spain", "Madrid"));
        locationService.save(new Location("Italy", "Rome"));
        locationService.save(new Location("Russia", "Moscow"));
        locationService.save(new Location("USA", "New York"));
        locationService.save(new Location("USA", "Chicago"));
    }
    private void addCategories() {
        categoryService.save(new Category("Fast Food"));
        categoryService.save(new Category("Barbecue"));
        categoryService.save(new Category("Ethnic"));
        categoryService.save(new Category("Bistro"));
    }
    private void addCousines() {
        cousineService.save(new Cousine("Bosnian"));
        cousineService.save(new Cousine("Italian"));
        cousineService.save(new Cousine("Mexican"));
        cousineService.save(new Cousine("Chinese"));
    }
    private void addRestaurants() {
        Location location = locationService.getById((long) 1).get();
        Category category = categoryService.getById((long) 14).get();
        Cousine cousine = cousineService.getById((long) 18).get();
        restaurantService.save(new Restaurant("U2", "Restaurant description", "/assets/images/rest1.jpg","placeholder",1,location,category,cousine));

        location = locationService.getById((long) 2).get();
        category = categoryService.getById((long) 15).get();
        cousine = cousineService.getById((long) 19).get();
        restaurantService.save(new Restaurant("Metropolis", "Restaurant description", "/assets/images/rest2.jpg","placeholder",2,location,category,cousine));

        location = locationService.getById((long) 3).get();
        category = categoryService.getById((long) 16).get();
        cousine = cousineService.getById((long) 20).get();
        restaurantService.save(new Restaurant("McDonalds", "Restaurant description", "/assets/images/rest3.jpg","placeholder",3,location,category,cousine));

        location = locationService.getById((long) 4).get();
        category = categoryService.getById((long) 17).get();
        cousine = cousineService.getById((long) 21).get();
        restaurantService.save(new Restaurant("Burger King", "Restaurant description", "/assets/images/rest4.jpg","placeholder",4,location,category,cousine));

        location = locationService.getById((long) 1).get();
        category = categoryService.getById((long) 14).get();
        cousine = cousineService.getById((long) 18).get();
        restaurantService.save(new Restaurant("Montana", "Restaurant description", "/assets/images/rest1.jpg","placeholder",1,location,category,cousine));

        location = locationService.getById((long) 2).get();
        category = categoryService.getById((long) 15).get();
        cousine = cousineService.getById((long) 19).get();
        restaurantService.save(new Restaurant("In-N-Out", "Restaurant description", "/assets/images/rest2.jpg","placeholder",2,location,category,cousine));

        location = locationService.getById((long) 3).get();
        category = categoryService.getById((long) 16).get();
        cousine = cousineService.getById((long) 20).get();
        restaurantService.save(new Restaurant("Starbucks", "Restaurant description", "/assets/images/rest3.jpg","placeholder",3,location,category,cousine));

        location = locationService.getById((long) 4).get();
        category = categoryService.getById((long) 17).get();
        cousine = cousineService.getById((long) 21).get();
        restaurantService.save(new Restaurant("Olive Garden", "Restaurant description", "/assets/images/rest4.jpg","placeholder",4,location,category,cousine));

        location = locationService.getById((long) 1).get();
        category = categoryService.getById((long) 14).get();
        cousine = cousineService.getById((long) 18).get();
        restaurantService.save(new Restaurant("Taco Bell", "Restaurant description", "/assets/images/rest1.jpg","placeholder",1,location,category,cousine));

        location = locationService.getById((long) 2).get();
        category = categoryService.getById((long) 15).get();
        cousine = cousineService.getById((long) 19).get();
        restaurantService.save(new Restaurant("Arby's", "Restaurant description", "/assets/images/rest2.jpg","placeholder",2,location,category,cousine));

        location = locationService.getById((long) 3).get();
        category = categoryService.getById((long) 16).get();
        cousine = cousineService.getById((long) 20).get();
        restaurantService.save(new Restaurant("KFC", "Restaurant description", "/assets/images/rest3.jpg","placeholder",3,location,category,cousine));

        location = locationService.getById((long) 4).get();
        category = categoryService.getById((long) 17).get();
        cousine = cousineService.getById((long) 21).get();
        restaurantService.save(new Restaurant("Pizza Hut", "Restaurant description", "/assets/images/rest4.jpg","placeholder",4,location,category,cousine));

    }
}
