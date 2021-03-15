package yarema.project.toystore.controller.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractDTOApiController;
import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.service.v2.TypeServiceV2;

@RestController
@RequestMapping("/api/v2/type")
public class TypeControllerV2 extends AbstractDTOApiController<Type, RequestTypeDTO, ResponseTypeDTO> {

    public TypeControllerV2(TypeServiceV2 service) {
        super(service);
    }
}
