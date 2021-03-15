package yarema.project.toystore.controller.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractDTOApiController;
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.service.v2.WaybillServiceV2;

@RestController
@RequestMapping("/api/v2/waybill")
public class WaybillControllerV2 extends AbstractDTOApiController<Waybill, RequestWaybillDTO, ResponseWaybillDTO> {
    public WaybillControllerV2(WaybillServiceV2 service) {
        super(service);
    }
}
