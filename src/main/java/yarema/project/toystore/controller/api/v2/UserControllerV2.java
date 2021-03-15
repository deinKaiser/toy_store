package yarema.project.toystore.controller.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractDTOApiController;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.service.v2.UserServiceV2;

@RestController
@RequestMapping("/api/v2/user")
public class UserControllerV2 extends AbstractDTOApiController<User, RequestUserDTO, ResponseUserDTO> {
    public UserControllerV2(UserServiceV2 service) {
        super(service);
    }
}
