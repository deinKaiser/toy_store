package yarema.project.toystore.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractCRUDController;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.service.AbstractCRUDService;

@RestController
@RequestMapping("/api/v1/waybill")
public class WaybillController extends AbstractCRUDController<Waybill> {
    public WaybillController(AbstractCRUDService<Waybill> service) {
        super(service);
    }
}
