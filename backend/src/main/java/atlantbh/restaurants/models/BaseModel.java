package atlantbh.restaurants.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseModel<M> {

    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Table ID cannot be null")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract M duplicate(M model);

    public abstract void update(M data);
}
