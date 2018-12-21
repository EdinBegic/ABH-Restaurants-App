package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Cousine extends BaseModel<Cousine> {
    // for now only atribute
    private String name;
    private Collection<Restaurant> restaurants;

    public Cousine(String name) {
        this.name = name;
    }

    public Cousine() {
    }

    @NotBlank(message = "Name of cousine cannot be null or whitespace")
    @Size(max = 50, message = "Name of cousine cannot be longer than 50 characters")
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "cousine")
    @JsonIgnore
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public Cousine duplicate(Cousine model) {
        Cousine cousine = new Cousine();
        cousine.setName(model.getName());
        cousine.setRestaurants(model.getRestaurants());
        return cousine;
    }

    @Override
    public void update(Cousine data) {
        setName(data.getName());
        setRestaurants(data.getRestaurants());
    }
}
