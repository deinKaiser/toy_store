package yarema.project.toystore.dto.warehouse;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class ResponseWarehouseDTO implements IDTO {

    private Long id;

    private String name;

    private String adress;

    private String phone;

}
