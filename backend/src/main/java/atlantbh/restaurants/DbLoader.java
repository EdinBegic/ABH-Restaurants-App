package atlantbh.restaurants;

import atlantbh.restaurants.models.*;
import atlantbh.restaurants.models.filters.*;
import atlantbh.restaurants.services.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DbLoader implements CommandLineRunner {
    private static final int NUM_OF_TABLES = 1;
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
    @Autowired
    RestaurantTableService restaurantTableService;
    @Autowired
    ReservationService reservationService;

    @Override
    public void run(String... args) throws Exception {
        addLocations();
        addUsers();
        addCategories();
        addCousines();
        addRestaurants();
        addMenusAndItems();
        addTablesAndReservations();
    }

    private void addUsers() {
        Location loc = locationService.findById((long) 2);
        userService.create(new User("Admin", "Admin", "admin@abh.com", "123-456-789", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.ADMIN, loc));
        loc = locationService.findById((long) 3);
        userService.create(new User("Edin", "Begic", "edinbegic@abh.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Kelli", "Hicks", "KelliMHicks@teleworm.us", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Ray", "Jones", "rayjones@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("John", "Miller", "jmiller@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Sandra", "Black", "sblack@yahoo.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Bill", "Jordan", "bjordan@msn.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Silvia", "Puck", "spuck@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("James", "Harris", "jharis@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Karen", "Murray", "murray@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Derrick", "Fay", "dfay@yahoo.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Solomon", "Hill", "shill@gmail.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));
        userService.create(new User("Marlene", "Selvik", "mar_selvik@msn.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.USER, loc));


    }

    private void addLocations() {
        locationService.create(new Location("Bosnia and Herzegovina", "Sarajevo"));
        locationService.create(new Location("Bosnia and Herzegovina", "Tuzla"));
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
        cousineService.create(new Cousine("Thai"));
    }

    private void addRestaurants() {
        LocationFilterBuilder lfb = new LocationFilterBuilder();
        CategoryFilterBuilder cfb = new CategoryFilterBuilder();
        CousineFilterBuilder cofb = new CousineFilterBuilder();
        LocationFilterBuilder lfbNear = new LocationFilterBuilder().setCity("Sarajevo");
        Location locNear = locationService.filter(lfbNear).getData().get(0);
        Location location = locationService.filter(lfb).getData().get(0);
        Category category = categoryService.filter(cfb).getData().get(0);
        Cousine cousine = cousineService.filter(cofb).getData().get(0);
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eu libero nec eros pharetra cursus. Sed consectetur vestibulum aliquam. Aliquam semper facilisis purus. Fusce vitae elementum justo, ac pulvinar enim. Morbi at nisi at neque convallis laoreet. Nunc tincidunt lacus vitae efficitur condimentum. Mauris gravida turpis sed ipsum mattis elementum. Duis tincidunt libero a nisl ultrices, quis hendrerit nulla convallis. Praesent scelerisque risus eget purus fermentum, id tincidunt magna vehicula. Phasellus tempus cursus blandit. Vestibulum velit sem, finibus et enim eget, bibendum tincidunt odio. Proin in dolor sed lorem mattis ornare in vel mauris. Integer ut finibus libero. Suspendisse tristique mollis odio nec tincidunt.";
        String coverLogoPath = "/assets/images/cover.jpg";
        GeometryFactory gf = new GeometryFactory();
        Coordinate coord = new Coordinate(43.88078048481765, 18.38896276559467, 0.0);
        Point coordinates = gf.createPoint(coord);
        restaurantService.create(new Restaurant("U2", description, "/assets/images/rest1.jpg", coverLogoPath, 1, locNear, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(0);
        category = categoryService.filter(cfb).getData().get(1);
        cousine = cousineService.filter(cofb).getData().get(1);
        coordinates = gf.createPoint(new Coordinate(43.870292553395295, 18.409144909300267, 0.0));
        restaurantService.create(new Restaurant("Metropolis", description, "/assets/images/rest2.jpg", coverLogoPath, 2, locNear, category, cousine, coordinates));

        category = categoryService.filter(cfb).getData().get(2);
        cousine = cousineService.filter(cofb).getData().get(2);
        coordinates = gf.createPoint(new Coordinate(43.859278, 18.414896, 0.0));
        restaurantService.create(new Restaurant("McDonalds", description, "/assets/images/rest3.jpg", coverLogoPath, 3, locNear, category, cousine, coordinates));

        category = categoryService.filter(cfb).getData().get(3);
        cousine = cousineService.filter(cofb).getData().get(3);
        coordinates = gf.createPoint(new Coordinate(43.861515, 18.437634, 0.0));
        restaurantService.create(new Restaurant("Burger King", description, "/assets/images/rest4.jpg", coverLogoPath, 4, locNear, category, cousine, coordinates));
/*
        category = categoryService.filter(cfb).getData().get(0);
        cousine = cousineService.filter(cofb).getData().get(0);
        coordinates = gf.createPoint(new Coordinate(43.864733, 18.421926, 0.0));
        restaurantService.create(new Restaurant("Montana", description, "/assets/images/rest1.jpg", coverLogoPath, 1, locNear, category, cousine, coordinates));

        category = categoryService.filter(cfb).getData().get(0);
        cousine = cousineService.filter(cofb).getData().get(0);
        coordinates = gf.createPoint(new Coordinate(43.848394, 43.848394, 0.0));
        restaurantService.create(new Restaurant("In-N-Out", description, "/assets/images/rest2.jpg", coverLogoPath, 2, locNear, category, cousine, coordinates));

        category = categoryService.filter(cfb).getData().get(1);
        cousine = cousineService.filter(cofb).getData().get(1);
        coordinates = gf.createPoint(new Coordinate(44.529801, 18.687916, 0.0));
        restaurantService.create(new Restaurant("Starbucks", description, "/assets/images/rest3.jpg", coverLogoPath, 3, locNear, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(1);
        category = categoryService.filter(cfb).getData().get(2);
        cousine = cousineService.filter(cofb).getData().get(2);
        coordinates = gf.createPoint(new Coordinate(44.78597635332904, 20.466732944172577, 0.0));
        restaurantService.create(new Restaurant("Olive Garden", description, "/assets/images/rest4.jpg", coverLogoPath, 4, location, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(2);
        category = categoryService.filter(cfb).getData().get(3);
        cousine = cousineService.filter(cofb).getData().get(3);
        coordinates = gf.createPoint(new Coordinate(45.80709138958274, 15.977979337780425, 0.0));
        restaurantService.create(new Restaurant("Taco Bell", description, "/assets/images/rest1.jpg", coverLogoPath, 1, location, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(3);
        category = categoryService.filter(cfb).getData().get(0);
        cousine = cousineService.filter(cofb).getData().get(0);
        coordinates = gf.createPoint(new Coordinate(45.807091, 15.945364, 0.0));
        restaurantService.create(new Restaurant("Arby's", description, "/assets/images/rest2.jpg", coverLogoPath, 2, location, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(3);
        category = categoryService.filter(cfb).getData().get(1);
        cousine = cousineService.filter(cofb).getData().get(1);
        coordinates = gf.createPoint(new Coordinate(45.805655, 15.915495, 0.0));

        restaurantService.create(new Restaurant("KFC", description, "/assets/images/rest3.jpg", coverLogoPath, 3, location, category, cousine, coordinates));

        location = locationService.filter(lfb).getData().get(3);
        category = categoryService.filter(cfb).getData().get(2);
        cousine = cousineService.filter(cofb).getData().get(2);
        coordinates = gf.createPoint(new Coordinate(45.812835, 15.990339, 0.0));
        restaurantService.create(new Restaurant("Pizza Hut", description, "/assets/images/rest4.jpg", coverLogoPath, 4, location, category, cousine, coordinates));
*/
    }

    private void addTablesAndReservations() throws ParseException {
        RestaurantFilterBuilder rfb = new RestaurantFilterBuilder()
                .setPageSize(0);
        // Admin adds initial resevations needed for reservation logic
        UserFilterBuilder ufb = new UserFilterBuilder();
        User user = userService.filter(ufb).getData().get(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));

        for (Restaurant r : restaurantService.filter(rfb).getData()) {
            for (int i = 0; i < NUM_OF_TABLES; i++) {
                ArrayList<Integer> tableSizes = new ArrayList<Integer>() {
                    {
                        add(2);
                        add(3);
                        add(4);
                        add(6);
                        add(8);
                        add(10);
                    }
                };
                for (Integer ts : tableSizes) {
                    RestaurantTable table = restaurantTableService.create(new RestaurantTable(ts, r));
                    Random rand = new Random();
                    long oneMinuteInMilis = 60000;
                    Integer year = 2018;
                    Integer day = 1;
                    Integer baseStayingPeriodMinutes = 30;
                    Calendar cal = Calendar.getInstance();
                    List<Integer> offset = new ArrayList<>();
                    for (int hour = 9; hour <= 22; hour++) {
                        if (hour < 12) {
                            offset.add(0);
                        } else if (hour < 15) {
                            offset.add(15);
                            offset.add(30);
                        } else if (hour < 18) {
                            offset.add(30);
                            offset.add(45);
                        } else if (hour < 23) {
                            offset.add(45);
                            offset.add(60);
                        }
                        for (int minutes = 0; minutes < 60; minutes += 30) {
                            for (int month = 0; month < 2; month++) {
                                cal.clear();
                                cal.set(year, month, day, hour, minutes, 0);
                                Random randomizer = new Random();
                                Integer randomOffset = offset.get(randomizer.nextInt(offset.size()));
                                Date startTime = cal.getTime();
                                Date createdAt = startTime;
                                Date endTime = new Date(startTime.getTime() + (baseStayingPeriodMinutes + randomOffset) * oneMinuteInMilis);
                                reservationService.create(new Reservation(startTime, endTime, user, table, createdAt, true));
                            }
                            day++;
                        }
                        year--;
                        day = 1;
                    }
                }

            }
        }
    }

    private void addMenusAndItems() {
        RestaurantFilterBuilder rfb = new RestaurantFilterBuilder();
        List<Restaurant> restaurantList = restaurantService.filter(rfb).getData();
        for (Restaurant restaurant : restaurantList) {
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
