package atlantbh.restaurants.exceptions;

/**
 * This class is inherited from the base Exception class and
 * represents an exception that is thrown
 * when a property is set on value that is considered
 * unique for all instances of the class.
 */
public class PropertyReservedException extends Exception {
    public PropertyReservedException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
