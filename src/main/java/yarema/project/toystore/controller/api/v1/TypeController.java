package yarema.project.toystore.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractCRUDController;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.service.AbstractCRUDService;

@RestController
@RequestMapping("/api/v1/type")
public class TypeController extends AbstractCRUDController<Type> {
    public TypeController(AbstractCRUDService<Type> service) {
        super(service);
    }
}
