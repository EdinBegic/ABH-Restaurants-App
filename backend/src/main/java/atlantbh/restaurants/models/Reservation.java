package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Reservation extends BaseModel<Reservation>{

    private Date startTime;
    private Date stayingPeriod;
    private User user;
    private RestaurantTable restaurantTable;
    private Date createdAt;
    private Boolean confirmed;

    public Reservation(Date startTime, Date stayingPeriod, User user, RestaurantTable restaurantTable, Date createdAt, Boolean confirmedReservation) {
        this.startTime = startTime;
        this.stayingPeriod = stayingPeriod;
        this.user = user;
        this.restaurantTable = restaurantTable;
        this.createdAt = createdAt;
        this.confirmed = confirmedReservation;
    }

    public Reservation() {

    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    @NotNull(message = "Time of reservation cannot be null")
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    @NotNull(message = "Staying period of reservation cannot be null")
    @Column(name = "staying_period")
    public Date getStayingPeriod() {
        return stayingPeriod;
    }

    public void setStayingPeriod(Date stayingPeriod) {
        this.stayingPeriod = stayingPeriod;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id", referencedColumnName = "id", nullable = false)
    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public Reservation duplicate(Reservation model) {
        Reservation reservation = new Reservation();
        reservation.setStartTime(model.getStartTime());
        reservation.setStayingPeriod(model.getStayingPeriod());
        reservation.setRestaurantTable(model.getRestaurantTable());
        reservation.setUser(model.getUser());
        reservation.setConfirmed(model.getConfirmed());
        // not sure for this setter
        reservation.setCreatedAt(model.getCreatedAt());
        return reservation;
    }

    @Override
    public void update(Reservation data) {
        setStartTime(data.getStartTime());
        setStayingPeriod(data.getStayingPeriod());
        setRestaurantTable(data.getRestaurantTable());
        setUser(data.getUser());
        setConfirmed(data.getConfirmed());
        // not sure for this setter
        setCreatedAt(data.getCreatedAt());
    }

}
