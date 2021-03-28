package yarema.project.toystore.stubs;

import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;

public final class WarehouseStub {
    public static final Long ID = 1L;
    public static Warehouse getWarehouse() {
        return Warehouse.builder()
                .id(ID)
                .name("My warehouse")
                .adress("holovna st. 228")
                .phone("380951337322")
                .build();
    }

    public static RequestWarehouseDTO getRequestDTO() {
        return RequestWarehouseDTO.builder()
                .name("My warehouse")
                .adress("holovna st. 228")
                .phone("380951337322")
                .build();
    }

    public static ResponseWarehouseDTO getResponseDTO() {
        return ResponseWarehouseDTO.builder()
                .name("My warehouse")
                .adress("holovna st. 228")
                .phone("380951337322")
                .build();
    }
}
