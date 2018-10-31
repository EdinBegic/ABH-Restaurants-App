package atlantbh.restaurants.exceptions;

public class StringMissmatchException extends Exception {
    public StringMissmatchException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
