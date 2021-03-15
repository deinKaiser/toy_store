package yarema.project.toystore.controller.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractDTOApiController;
import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.service.v2.ProductServiceV2;

@RestController
@RequestMapping("/api/v2/product")
public class ProductControllerV2 extends AbstractDTOApiController<Product, RequestProductDTO, ResponseProductDTO> {
    public ProductControllerV2(ProductServiceV2 service) {
        super(service);
    }
}
