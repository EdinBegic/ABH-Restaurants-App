package atlantbh.restaurants.models.dto;

import atlantbh.restaurants.models.RestaurantTable;

import java.util.Date;
import java.util.List;

public class ReservationResponseDTO {

    private List<Date> suggestedDates;
    private Integer availableTables;
    private List<List<RestaurantTable>> restaurantTables;
    public ReservationResponseDTO(List<Date> suggestedDates, Integer availableTables, List<List<RestaurantTable>> restaurantTables) {
        this.suggestedDates = suggestedDates;
        this.availableTables = availableTables;
        this.restaurantTables = restaurantTables;
    }

    public List<Date> getSuggestedDates() {
        return suggestedDates;
    }

    public void setSuggestedDates(List<Date> suggestedDates) {
        this.suggestedDates = suggestedDates;
    }

    public Integer getAvailableTables() {
        return availableTables;
    }

    public void setAvailableTables(Integer availableTables) {
        this.availableTables = availableTables;
    }

    public List<List<RestaurantTable>> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(List<List<RestaurantTable>> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }
}
