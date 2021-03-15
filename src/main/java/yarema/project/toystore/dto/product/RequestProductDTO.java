package yarema.project.toystore.dto.product;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class RequestProductDTO implements IDTO {

    private String name;

    private Integer count;

    private Long typeId;

    private Long warehouseId;

}
