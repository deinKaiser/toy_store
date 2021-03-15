package yarema.project.toystore.dto.user;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class ResponseUserDTO implements IDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String email;
}
