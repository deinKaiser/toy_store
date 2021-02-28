package yarema.project.toystore.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractCRUDController;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.service.AbstractCRUDService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractCRUDController<User> {
    public UserController(AbstractCRUDService<User> service) {
        super(service);
    }
}
