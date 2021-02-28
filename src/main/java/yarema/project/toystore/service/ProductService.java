package yarema.project.toystore.service;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.repository.ProductRepository;

@Service
public class ProductService extends AbstractCRUDService<Product>{
    public ProductService(ProductRepository repository) {super(repository);}
}
