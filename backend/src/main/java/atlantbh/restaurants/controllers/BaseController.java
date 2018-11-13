package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.BaseModel;
import atlantbh.restaurants.services.BaseService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

public abstract class BaseController<M extends BaseModel<M>, S extends BaseService<M, ?, ?, ?>> {

    protected S service;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    @Transactional
    public ResponseEntity create(@RequestBody M model) {
        try {
            return ResponseEntity.ok(service.create(model));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity update(@RequestParam Long id, @RequestBody M model) {
        try {
            return ResponseEntity.ok(service.update(id, model));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity get(@PathVariable("id") Long id) {
        M modelInstance = service.findById(id);
        if (modelInstance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelInstance);
    }

    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}