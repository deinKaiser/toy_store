package yarema.project.toystore.dto.product;

import lombok.Data;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class ResponseProductDTO implements IDTO {

    private Long id;

    private String name;

    private Integer count;

    private ResponseTypeDTO type;

    private ResponseWarehouseDTO warehouse;

}
