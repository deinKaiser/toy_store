package yarema.project.toystore.interfaces.services;

import javassist.NotFoundException;
import yarema.project.toystore.entity.User;

public interface IUserDetailsService {
    User loadUserByEmail(String email) throws NotFoundException;
}
