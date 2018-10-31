package atlantbh.restaurants.exceptions;

import org.hibernate.service.spi.ServiceException;

public class RepositoryException extends ServiceException {
    public RepositoryException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
