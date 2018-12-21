package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    private Point coordinates;
    private Collection<Review> reviews;
    private Collection<RestaurantTable> restaurantTables;
    private Collection<Menu> menus;
    public Restaurant(String name, String description, String logoPath, String coverPhotoPath, Integer priceRange, Location location, Category category, Cousine cousine, Point coordinates) {
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
        this.coverPhotoPath = coverPhotoPath;
        this.priceRange = priceRange;
        this.location = location;
        this.category = category;
        this.cousine = cousine;
        this.coordinates = coordinates;
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

    @OneToMany(mappedBy = "restaurant", orphanRemoval = true)
    @JsonIgnore
    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
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

    @OneToMany(mappedBy = "restaurant", orphanRemoval = true)
    @JsonIgnore
    public Collection<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(Collection<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

    @Type(type = "jts_geometry")
    @JsonIgnore
    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    @OneToMany(mappedBy = "restaurant", orphanRemoval = true)
    @JsonIgnore
    public Collection<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Collection<Menu> menus) {
        this.menus = menus;
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
        restaurant.setMenus(model.getMenus());
        restaurant.setCoordinates(model.getCoordinates());
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
        setMenus(data.getMenus());
        setCoordinates(data.getCoordinates());
    }

    public static class RestaurantCoordinatesDto {
        private String name;
        private Double lat;
        private Double lng;
        private Integer id;

        public RestaurantCoordinatesDto() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}
