package yarema.project.toystore.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO implements IDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String email;
}
