package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
        if (data.getName() != null) setName(data.getName());
        if (data.getRestaurants() != null) setRestaurants(data.getRestaurants());
    }
}
