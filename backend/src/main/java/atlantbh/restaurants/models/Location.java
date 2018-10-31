package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Location extends BaseModel<Location> {


    private String country;
    private String city;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Collection<User> users;
    private Collection<Restaurant> restaurants;

    public Location(String country, String city, BigDecimal longitude, BigDecimal latitude) {
        this.country = country;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(String country, String city) {
        this.country = country;
        this.city = city;
    }

    protected Location() {
    }

    @NotBlank(message = "Name of country cannot be null or whitespace")
    @Size(max = 50, message = "Name of country cannot be longer than 50 characters")
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotBlank(message = "Name of city cannot be null or whitespace")
    @Size(max = 50, message = "Name of city cannot be longer than 50 characters")
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "longitude")
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude")
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public Location duplicate(Location model) {
        Location location = new Location();
        location.setCity(model.getCity());
        location.setCountry(model.getCountry());
        location.setLatitude(model.getLatitude());
        location.setLongitude(model.getLongitude());
        location.setRestaurants(model.getRestaurants());
        location.setUsers(model.getUsers());
        return location;
    }

    @Override
    public void update(Location data) {
        if (data.getRestaurants() != null) setRestaurants(data.getRestaurants());
        if (data.getCity() != null) setCity(data.getCity());
        if (data.getCountry() != null) setCountry(data.getCountry());
        if (data.getLatitude() != null) setLatitude(data.getLatitude());
        if (data.getLongitude() != null) setLongitude(data.getLongitude());
        if (data.getUsers() != null) setUsers(data.getUsers());
    }
}
