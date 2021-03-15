package yarema.project.toystore.dto.warehouse;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class RequestWarehouseDTO implements IDTO {

    private String name;

    private String adress;

    private String phone;

}
