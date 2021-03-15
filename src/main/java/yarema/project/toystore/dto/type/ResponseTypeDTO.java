package yarema.project.toystore.dto.type;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class ResponseTypeDTO implements IDTO {

    private Long id;

    private String name;
}
