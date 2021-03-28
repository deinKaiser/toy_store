package yarema.project.toystore.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWarehouseDTO implements IDTO {

    private Long id;

    private String name;

    private String adress;

    private String phone;

}
