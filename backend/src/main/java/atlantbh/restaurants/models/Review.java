package atlantbh.restaurants.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Review extends BaseModel<Review> {

    private float mark;
    private String comment;
    private User user;
    private Restaurant restaurant;

    public Review(float mark, String comment, User user, Restaurant restaurant) {
        this.mark = mark;
        this.comment = comment;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Review() {
    }

    @Min(value = 0, message = "Please select a rating between 0 and 5")
    @Max(value = 5, message = "Please select a rating between 0 and 5")
    @Column(name = "mark")
    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Size(max = 2000, message = "Comment cannot be longer than 2000 characters")
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public Review duplicate(Review model) {
        Review review = new Review();
        review.setComment(model.getComment());
        review.setMark(model.getMark());
        review.setRestaurant(model.getRestaurant());
        review.setUser(model.getUser());
        return review;
    }

    @Override
    public void update(Review data) {
        setComment(data.getComment());
        setMark(data.getMark());
        setRestaurant(data.getRestaurant());
        setUser(data.getUser());
    }
}
