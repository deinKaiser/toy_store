package yarema.project.toystore.dto.waybill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestWaybillDTO implements IDTO {

    private Integer count;

    private String waybillCode;

    private Long userId;

    private Long productId;
}
