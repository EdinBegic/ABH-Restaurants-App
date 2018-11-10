package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Restaurant extends BaseModel<Restaurant> {

    private String name;
    private String description;
    private String logoPath;
    private String coverPhotoPath;
    private Integer priceRange;
    private Location location;
    private Category category;
    private Cousine cousine;
    private Long reviewSize;
    private Double avgRating;
    private Collection<Review> reviews;
    private Collection<RestaurantTable> restaurantTables;

    public Restaurant(String name, String description, String logoPath, String coverPhotoPath, Integer priceRange, Location location, Category category, Cousine cousine) {
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
        this.coverPhotoPath = coverPhotoPath;
        this.priceRange = priceRange;
        this.location = location;
        this.category = category;
        this.cousine = cousine;
        this.avgRating = 0D;
        this.reviewSize = 0L;
    }

    public Restaurant() {
    }

    @NotBlank(message = "Name of restaurant cannot be null or whitespace")
    @Size(max = 50, message = "Restaurant name cannot be longer than 50 characters")
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 2000, message = "Restaurant name cannot be longer than 2000 characters")
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Some browsers cannot parse urls that are longer
    @Size(max = 2000, message = "Url path cannot be longer than 2000 characters")
    @Column(name = "logo_path")
    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Size(max = 2000, message = "Url path cannot be longer than 2000 characters")
    @Column(name = "cover_photo_path")
    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    @Min(value = 1, message = "Please select a rating between 1 and 4")
    @Max(value = 4, message = "Please select a rating between 1 and 4")
    @Column(name = "price_range")
    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "cousine_id", referencedColumnName = "id", nullable = false)
    public Cousine getCousine() {
        return cousine;
    }

    public void setCousine(Cousine cousine) {
        this.cousine = cousine;
    }

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    public Collection<Review> getReviews() {
        return reviews;
    }

    @Column(name = "review_size")
    public Long getReviewSize() {
        return reviewSize;
    }

    public void setReviewSize(Long reviewSize) {
        this.reviewSize = reviewSize;
    }

    @Column(name = "avg_rating")
    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }


    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    public Collection<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(Collection<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }
    @Override
    public Restaurant duplicate(Restaurant model) {
        Restaurant restaurant = new Restaurant();
        restaurant.setCategory(model.getCategory());
        restaurant.setCousine(model.getCousine());
        restaurant.setCoverPhotoPath(model.getCoverPhotoPath());
        restaurant.setDescription(model.getDescription());
        restaurant.setLocation(model.getLocation());
        restaurant.setLogoPath(model.getLogoPath());
        restaurant.setName(model.getName());
        restaurant.setPriceRange(model.getPriceRange());
        restaurant.setAvgRating(model.getAvgRating());
        restaurant.setReviewSize(model.getReviewSize());
        restaurant.setRestaurantTables(model.getRestaurantTables());
        return restaurant;
    }

    @Override
    public void update(Restaurant data) {
        setCategory(data.getCategory());
        setCousine(data.getCousine());
        setCoverPhotoPath(data.getCoverPhotoPath());
        setDescription(data.getDescription());
        setLocation(data.getLocation());
        setLogoPath(data.getLogoPath());
        setName(data.getName());
        setPriceRange(data.getPriceRange());
        setAvgRating(data.getAvgRating());
        setReviewSize(data.getReviewSize());
        setRestaurantTables(data.getRestaurantTables());
    }

}
