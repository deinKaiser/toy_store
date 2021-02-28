package yarema.project.toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yarema.project.toystore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
