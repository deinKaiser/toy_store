package yarema.project.toystore.service;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.repository.UserRepository;

@Service
public class UserService extends AbstractCRUDService<User> {
    public UserService(UserRepository repository) {super(repository);}
}
