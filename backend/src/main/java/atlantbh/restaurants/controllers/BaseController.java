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
        M modelInstance = service.create(model);
        return ResponseEntity.ok(modelInstance);
    }

    @Transactional
    public ResponseEntity update(@RequestParam Long id, @RequestParam M model) {
        M modelInstance = service.update(id, model);
        return ResponseEntity.ok(modelInstance);
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
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}