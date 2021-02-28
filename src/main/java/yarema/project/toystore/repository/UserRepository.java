package yarema.project.toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yarema.project.toystore.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
