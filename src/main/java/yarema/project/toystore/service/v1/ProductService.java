package yarema.project.toystore.service.v1;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.repository.ProductRepository;
import yarema.project.toystore.service.AbstractCRUDService;

@Service
public class ProductService extends AbstractCRUDService<Product> {
    public ProductService(ProductRepository repository) {super(repository);}
}
