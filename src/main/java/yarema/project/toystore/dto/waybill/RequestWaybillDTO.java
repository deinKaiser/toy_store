package yarema.project.toystore.dto.waybill;

import lombok.Data;
import yarema.project.toystore.interfaces.IDTO;

@Data
public class RequestWaybillDTO implements IDTO {

    private Integer count;

    private String waybillCode;

    private Long userId;

    private Long productId;
}
