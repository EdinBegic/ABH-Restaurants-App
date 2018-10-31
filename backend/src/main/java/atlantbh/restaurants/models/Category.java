package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Category extends BaseModel<Category> {

    private String name;
    private Collection<Restaurant> restaurants;

    public Category(String category) {
        this.name = category;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    @Override
    public Category duplicate(Category model) {
        Category category = new Category();
        category.setName(model.getName());
        category.setRestaurants(model.getRestaurants());
        return category;
    }

    @Override
    public void update(Category data) {
        if (data.getName() != null) setName(data.getName());
        if (data.getRestaurants() != null) setRestaurants(data.getRestaurants());
    }
}
