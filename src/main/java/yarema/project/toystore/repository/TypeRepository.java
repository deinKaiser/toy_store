package yarema.project.toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yarema.project.toystore.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
