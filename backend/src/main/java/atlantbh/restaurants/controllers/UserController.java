package atlantbh.restaurants.controllers;

import atlantbh.restaurants.models.User;
import atlantbh.restaurants.models.dto.UserRegistrationDTO;
import atlantbh.restaurants.models.filters.UserFilterBuilder;
import atlantbh.restaurants.models.sortkeys.UserSortKeys;
import atlantbh.restaurants.services.UserService;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserService> {

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserRegistrationDTO user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            return ResponseEntity.ok(service.get(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "sortKey", required = false) String sortKey,
                                 @RequestParam(value = "sortAsc", required = false) Boolean sortAsc) {
        try {
            UserFilterBuilder ufb = new UserFilterBuilder()
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setPageSize(pageSize)
                    .setPageNumber(pageNumber)
                    .setSortAsc(sortAsc)
                    .setSortKey(EnumUtils.getEnum(UserSortKeys.class, sortKey));
            return ResponseEntity.ok(service.filter(ufb));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity update(@PathVariable Long id, @RequestBody User model) {
        try {
            return ResponseEntity.ok(service.update(id,model));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
