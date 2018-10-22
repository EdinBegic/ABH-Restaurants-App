package atlantbh.restaurants;

import atlantbh.restaurants.models.Location;
import atlantbh.restaurants.models.User;
import atlantbh.restaurants.services.LocationService;
import atlantbh.restaurants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbLoader implements CommandLineRunner {
    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        addLocations();
        addUsers();
    }

    private void addUsers() {
        Location loc = locationService.getById((long) 2).get();
        userService.save(new User("Admin", "Admin", "admin@abh.com", "123-456-789", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", User.RoleName.ADMIN, loc));
        loc = locationService.getById((long) 3).get();
        userService.save(new User("Edin", "Begic", "edinbegic@abh.com", "061-225-883", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", User.RoleName.USER, loc));
    }

    private void addLocations() {
        locationService.save(new Location("Bosnia", "Sarajevo"));
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
}
