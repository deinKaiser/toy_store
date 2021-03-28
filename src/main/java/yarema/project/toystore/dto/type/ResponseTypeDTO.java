package yarema.project.toystore.dto.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTypeDTO implements IDTO {

    private Long id;

    private String name;
}
