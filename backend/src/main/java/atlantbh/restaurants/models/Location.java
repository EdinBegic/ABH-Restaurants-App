package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;

@SqlResultSetMapping(
        name = "TopLocationMapping",
        entities = @EntityResult(
                entityClass = Location.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "country", column = "country"),
                        @FieldResult(name = "city", column = "city"),
                        @FieldResult(name = "longitude", column = "longitude"),
                        @FieldResult(name = "latitude", column = "latitude"),
                        @FieldResult(name = "borderRadius", column = "border_radius")}),
        columns = @ColumnResult(name = "numOfRestaurants", type = Long.class))

@Entity
public class Location extends BaseModel<Location> {

    private String country;
    private String city;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Collection<User> users;
    private Collection<Restaurant> restaurants;
    private Polygon borderRadius;

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

    public Location(String country, String city, Polygon borderRadius) {
        this.country = country;
        this.city = city;
        this.borderRadius = borderRadius;
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

    @OneToMany(mappedBy = "location", orphanRemoval = true)
    @JsonIgnore
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Column(name = "border_radius", columnDefinition = "geometry(Polygon, 4326)")
    public Polygon getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(Polygon borderRadius) {
        this.borderRadius = borderRadius;
    }

    @Override
    public Location duplicate(Location model) {
        Location location = new Location();
        location.setCity(model.getCity());
        location.setCountry(model.getCountry());
        location.setLatitude(model.getLatitude());
        location.setLongitude(model.getLongitude());
        location.setBorderRadius(model.getBorderRadius());
        location.setRestaurants(model.getRestaurants());
        location.setUsers(model.getUsers());
        return location;
    }

    @Override
    public void update(Location data) {
        setRestaurants(data.getRestaurants());
        setCity(data.getCity());
        setCountry(data.getCountry());
        setLatitude(data.getLatitude());
        setLongitude(data.getLongitude());
        setBorderRadius(data.getBorderRadius());
        setUsers(data.getUsers());
    }
}
