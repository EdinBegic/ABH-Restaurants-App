package atlantbh.restaurants.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReservationDTO {
    private Long userId;
    private Integer sittingPlaces;
    private Long restaurantId;
    private String startDate;
    //HH:mm:ss
    private String startTime;

    public ReservationDTO(Long userId, Long restaurantId, String startDate, String startTime, Integer sittingPlaces) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.sittingPlaces = sittingPlaces;
        this.startDate = startDate;
        this.startTime = startTime;
    }
    public ReservationDTO() {

    }

    @NotNull
    @Positive
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotBlank
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @NotBlank
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @NotNull
    @Positive
    public Integer getSittingPlaces() {
        return sittingPlaces;
    }

    public void setSittingPlaces(Integer sittingPlaces) {
        this.sittingPlaces = sittingPlaces;
    }

    @NotNull
    @Positive
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
