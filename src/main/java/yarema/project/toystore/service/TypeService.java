package yarema.project.toystore.service;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.repository.TypeRepository;

@Service
public class TypeService extends AbstractCRUDService<Type> {
    public TypeService(TypeRepository repository) {super(repository);}
}
