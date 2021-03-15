package yarema.project.toystore.dto.waybill;

import lombok.Data;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.interfaces.IDTO;


@Data
public class ResponseWaybillDTO implements IDTO {

    private Long id;

    private Integer count;

    private String waybillCode;

    private ResponseUserDTO user;

    private ResponseProductDTO product;

}
