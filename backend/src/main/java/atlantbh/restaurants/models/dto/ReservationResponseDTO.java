package atlantbh.restaurants.models.dto;

import java.util.Date;
import java.util.List;

public class ReservationResponseDTO {

    private List<Date> suggestedDates;
    private Integer availableTables;

    public ReservationResponseDTO(List<Date> suggestedDates, Integer availableTables) {
        this.suggestedDates = suggestedDates;
        this.availableTables = availableTables;
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
}
