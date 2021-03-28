package yarema.project.toystore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDTO implements IDTO {

    private String name;

    private Integer count;

    private Long typeId;

    private Long warehouseId;

}
