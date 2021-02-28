package yarema.project.toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yarema.project.toystore.entity.Waybill;

public interface WaybillRepository extends JpaRepository<Waybill, Long> {
}
