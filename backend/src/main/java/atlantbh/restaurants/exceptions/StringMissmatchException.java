package atlantbh.restaurants.exceptions;

/**
 * This class is inherited from the base Exception class and
 * represents a exception that is thrown
 * when two strings, which need to be equal, are compared
 * and don't match.
 */
public class StringMissmatchException extends Exception {
    public StringMissmatchException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
