package yarema.project.toystore.stubs;

import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.entity.Product;

public final class ProductStub {
    public static final Long ID = 1L;
    public static Product getProduct(){
        return Product.builder().id(ID)
                .name("test product")
                .count(228)
                .type(TypeStub.getType())
                .warehouse(WarehouseStub.getWarehouse())
                .build();
    }

    public static RequestProductDTO getRequestDTO(){
        return RequestProductDTO.builder()
                .name("test product")
                .count(228)
                .typeId(ID)
                .warehouseId(ID)
                .build();
    }

    public static ResponseProductDTO getResponseDTO(){
        return ResponseProductDTO.builder().id(ID)
                .name("test product")
                .count(228)
                .type(TypeStub.getResponseDTO())
                .warehouse(WarehouseStub.getResponseDTO())
                .build();
    }
}
