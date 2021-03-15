package yarema.project.toystore.controller.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractDTOApiController;
import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.service.v2.WarehouseServiceV2;

@RestController
@RequestMapping("/api/v2/warehouse")
public class WarehouseControllerV2 extends AbstractDTOApiController<Warehouse, RequestWarehouseDTO, ResponseWarehouseDTO> {
    public WarehouseControllerV2(WarehouseServiceV2 service) {
        super(service);
    }
}
