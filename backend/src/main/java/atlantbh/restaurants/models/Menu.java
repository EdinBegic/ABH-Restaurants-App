package atlantbh.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Menu extends BaseModel<Menu> {
    private String menuType;
    private Restaurant restaurant;
    private Collection<MenuItem> menuItems;

    public Menu(String menuType, Restaurant restaurant) {
        this.menuType = menuType;
        this.restaurant = restaurant;
    }

    public Menu() {

    }


    @Override
    public Menu duplicate(Menu model) {
        return null;
    }

    @Override
    public void update(Menu data) {

    }

    @Column(name = "menu_type")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id",  nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @OneToMany(mappedBy = "menu", orphanRemoval = true)
    @JsonIgnore
    public Collection<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Collection<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
