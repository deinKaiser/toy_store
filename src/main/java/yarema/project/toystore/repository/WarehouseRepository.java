package yarema.project.toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yarema.project.toystore.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
