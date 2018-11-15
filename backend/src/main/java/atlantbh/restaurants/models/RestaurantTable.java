package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Collection;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable extends BaseModel<RestaurantTable> {
    private Integer sittingPlaces;
    private Restaurant restaurant;
    private Collection<Reservation> reservations;

    public RestaurantTable(Integer sittingPlaces, Restaurant restaurant) {
        this.sittingPlaces = sittingPlaces;
        this.restaurant = restaurant;
    }

    public RestaurantTable() {

    }

    @Min(value = 1, message = "Minimum size of tables is 1 sitting place")
    @Column(name = "sitting_places")
    public Integer getSittingPlaces() {
        return sittingPlaces;
    }

    public void setSittingPlaces(Integer sittingPlaces) {
        this.sittingPlaces = sittingPlaces;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @OneToMany(mappedBy = "restaurantTable")
    @JsonIgnore
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public RestaurantTable duplicate(RestaurantTable model) {
        RestaurantTable table = new RestaurantTable();
        table.setSittingPlaces(model.getSittingPlaces());
        table.setRestaurant(model.getRestaurant());
        table.setReservations(model.getReservations());
        return table;
    }

    @Override
    public void update(RestaurantTable data) {
        setSittingPlaces(data.getSittingPlaces());
        setReservations(data.getReservations());
        setRestaurant(data.getRestaurant());
    }
}
