package atlantbh.restaurants.models;

public class ErrorResponseWrapper {
    private DefaultError error;

    public ErrorResponseWrapper(DefaultError error) {
        this.error = error;
    }

    public ErrorResponseWrapper() {
    }

    public DefaultError getError() {
        return error;
    }

    public void setError(DefaultError error) {
        this.error = error;
    }
}
