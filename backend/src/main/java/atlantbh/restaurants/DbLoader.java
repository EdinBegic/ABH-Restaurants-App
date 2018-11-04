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
        Location loc = locationService.findById((long) 2);
        userService.create(new User("Admin", "Admin", "admin@abh.com", "123-456-789", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.ADMIN, loc));
        loc = locationService.findById((long) 3);
        userService.create(new User("Edin", "Begic", "edinbegic@abh.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
    }

    private void addLocations() {
        locationService.create(new Location("Bosnia", "Sarajevo"));
        locationService.create(new Location("Bosnia", "Tuzla"));
        locationService.create(new Location("Serbia", "Belgrade"));
        locationService.create(new Location("Croatia", "Zagreb"));
        locationService.create(new Location("Germany", "Berlin"));
        locationService.create(new Location("United Kingdom", "London"));
        locationService.create(new Location("Spain", "Madrid"));
        locationService.create(new Location("Italy", "Rome"));
        locationService.create(new Location("Russia", "Moscow"));
        locationService.create(new Location("USA", "New York"));
        locationService.create(new Location("USA", "Chicago"));
    }

    private void addCategories() {
        categoryService.create(new Category("Fast Food"));
        categoryService.create(new Category("Barbecue"));
        categoryService.create(new Category("Ethnic"));
        categoryService.create(new Category("Bistro"));
    }

    private void addCousines() {
        cousineService.create(new Cousine("Bosnian"));
        cousineService.create(new Cousine("Italian"));
        cousineService.create(new Cousine("Mexican"));
        cousineService.create(new Cousine("Chinese"));
    }


    private void addRestaurants() {
        Location location = locationService.findById((long) 1);
        Category category = categoryService.findById((long) 14);
        Cousine cousine = cousineService.findById((long) 18);
        restaurantService.create(new Restaurant("U2", "Restaurant description", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("Metropolis", "Restaurant description", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("McDonalds", "Restaurant description", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Burger King", "Restaurant description", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 14);
        cousine = cousineService.findById((long) 18);
        restaurantService.create(new Restaurant("Montana", "Restaurant description", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("In-N-Out", "Restaurant description", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 2);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("Starbucks", "Restaurant description", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Olive Garden", "Restaurant description", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 14);
        cousine = cousineService.findById((long) 18);
        restaurantService.create(new Restaurant("Taco Bell", "Restaurant description", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("Arby's", "Restaurant description", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("KFC", "Restaurant description", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Pizza Hut", "Restaurant description", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

    }

}
