package atlantbh.restaurants.exceptions;

public class PropertyReservedException extends Exception {
    public PropertyReservedException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
