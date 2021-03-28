package yarema.project.toystore.dto.waybill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.interfaces.IDTO;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWaybillDTO implements IDTO {

    private Long id;

    private Integer count;

    private String waybillCode;

    private ResponseUserDTO user;

    private ResponseProductDTO product;

}
