package atlantbh.restaurants.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseModel {

    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "User ID cannot be null")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
