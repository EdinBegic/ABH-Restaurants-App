package atlantbh.restaurants.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private Collection<Review> reviews;

    public Restaurant(String name, String description, String logoPath, String coverPhotoPath, Integer priceRange, Location location, Category category, Cousine cousine) {
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
        this.coverPhotoPath = coverPhotoPath;
        this.priceRange = priceRange;
        this.location = location;
        this.category = category;
        this.cousine = cousine;
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
    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
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
        return restaurant;
    }

    @Override
    public void update(Restaurant data) {
        if (data.getCategory() != null) setCategory(data.getCategory());
        if (data.getCousine() != null) setCousine(data.getCousine());
        if (data.getCoverPhotoPath() != null) setCoverPhotoPath(data.getCoverPhotoPath());
        if (data.getDescription() != null) setDescription(data.getDescription());
        if (data.getLocation() != null) setLocation(data.getLocation());
        if (data.getLogoPath() != null) setLogoPath(data.getLogoPath());
        if (data.getName() != null) setName(data.getName());
        if (data.getPriceRange() != null) setPriceRange(data.getPriceRange());
    }

}
