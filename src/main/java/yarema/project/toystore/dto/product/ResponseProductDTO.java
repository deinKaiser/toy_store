package yarema.project.toystore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO implements IDTO {

    private Long id;

    private String name;

    private Integer count;

    private ResponseTypeDTO type;

    private ResponseWarehouseDTO warehouse;

}
