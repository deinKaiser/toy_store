package yarema.project.toystore.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarema.project.toystore.controller.api.AbstractCRUDController;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.service.AbstractCRUDService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends AbstractCRUDController<Product> {
    public ProductController(AbstractCRUDService<Product> service) {
        super(service);
    }
}
