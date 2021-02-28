package yarema.project.toystore.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractCRUDController;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.service.AbstractCRUDService;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController extends AbstractCRUDController<Warehouse> {
    public WarehouseController(AbstractCRUDService<Warehouse> service) {
        super(service);
    }
}
