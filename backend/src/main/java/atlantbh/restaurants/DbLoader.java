package atlantbh.restaurants;

import atlantbh.restaurants.models.*;
import atlantbh.restaurants.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

    @Autowired
    MenuService menuService;

    @Autowired
    MenuItemService menuItemService;

    @Override
    public void run(String... args) throws Exception {
        addLocations();
        addUsers();
        addCategories();
        addCousines();
        addRestaurants();
        addMenusAndItems();
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
        restaurantService.create(new Restaurant("U2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("Metropolis", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("McDonalds", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Burger King", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 14);
        cousine = cousineService.findById((long) 18);
        restaurantService.create(new Restaurant("Montana", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 1);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("In-N-Out", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 2);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("Starbucks", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 3);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Olive Garden", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 14);
        cousine = cousineService.findById((long) 18);
        restaurantService.create(new Restaurant("Taco Bell", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest1.jpg", "placeholder", 1, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 15);
        cousine = cousineService.findById((long) 19);
        restaurantService.create(new Restaurant("Arby's", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest2.jpg", "placeholder", 2, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 16);
        cousine = cousineService.findById((long) 20);
        restaurantService.create(new Restaurant("KFC", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest3.jpg", "placeholder", 3, location, category, cousine));

        location = locationService.findById((long) 4);
        category = categoryService.findById((long) 17);
        cousine = cousineService.findById((long) 21);
        restaurantService.create(new Restaurant("Pizza Hut", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.\n" +
                "\n", "/assets/images/rest4.jpg", "placeholder", 4, location, category, cousine));

    }

    private void addMenusAndItems() {
        for(Restaurant restaurant: restaurantService.all()) {
            Menu menu = menuService.create(new Menu("Breakfast", restaurant));
            menuItemService.create(new MenuItem("Brocoli Rabe", "With grilled sausage, olive oil and garlic", new BigDecimal(8.95), menu));
            menuItemService.create(new MenuItem("Fried Mozzarella", null, new BigDecimal(3.42), menu));
            menuItemService.create(new MenuItem("Fried Calamari", null, new BigDecimal(10.2), menu));
            menuItemService.create(new MenuItem("Garlic Bread", null, new BigDecimal(2.30), menu));

            menu = menuService.create(new Menu("Lunch", restaurant));
            menuItemService.create(new MenuItem("Toast", null, new BigDecimal(2.30), menu));
            menuItemService.create(new MenuItem("Chicken sandwich", "With grilled sausage, olive oil and garlic", new BigDecimal(8.95), menu));
            menuItemService.create(new MenuItem("Cheeseburger", null, new BigDecimal(3.42), menu));
            menuItemService.create(new MenuItem("Steak", null, new BigDecimal(10.2), menu));

            menu = menuService.create(new Menu("Dinner", restaurant));
            menuItemService.create(new MenuItem("Soup", "With grilled sausage, olive oil and garlic", new BigDecimal(8.95), menu));
            menuItemService.create(new MenuItem("Lamb", null, new BigDecimal(3.42), menu));
            menuItemService.create(new MenuItem("Fish", null, new BigDecimal(10.2), menu));
        }
    }

}
