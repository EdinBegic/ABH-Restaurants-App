package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.BaseModel;
import atlantbh.restaurants.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public abstract class BaseController<M extends BaseModel, S extends BaseService<M, ?>> {
    protected S service;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    @ResponseBody
    public ResponseEntity<Object> all() {
        Collection<M> modelInstances = service.all();
        return ResponseEntity.ok(modelInstances);
    }

    @Transactional
    public ResponseEntity create(@RequestBody M model) {
        service.save(model);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<M> modelInstance = service.getById(id);
        if (!modelInstance.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelInstance);
    }

    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (!service.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}