package yarema.project.toystore.stubs;

import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;

public final class WaybillStub {
    public static final Long ID = 1L;
    public static Waybill getWaybill() {
        return Waybill.builder()
                .id(ID)
                .count(1)
                .waybillCode("dwHJa4e1wWD1rd")
                .user(UserStub.getUser())
                .product(ProductStub.getProduct())
                .build();
    }

    public static RequestWaybillDTO getRequestDTO() {
        return RequestWaybillDTO.builder()
                .count(1)
                .waybillCode("dwHJa4e1wWD1rd")
                .userId(ID)
                .productId(ID)
                .build();
    }

    public static ResponseWaybillDTO getResponseDTO() {
        return ResponseWaybillDTO.builder()
                .id(ID)
                .count(1)
                .waybillCode("dwHJa4e1wWD1rd")
                .user(UserStub.getResponseDTO())
                .product(ProductStub.getResponseDTO())
                .build();
    }
}
