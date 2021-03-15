package yarema.project.toystore.service.v1;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.repository.UserRepository;
import yarema.project.toystore.service.AbstractCRUDService;

@Service
public class UserService extends AbstractCRUDService<User> {
    public UserService(UserRepository repository) {super(repository);}
}
