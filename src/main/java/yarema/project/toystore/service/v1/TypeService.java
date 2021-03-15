package yarema.project.toystore.service.v1;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.repository.TypeRepository;
import yarema.project.toystore.service.AbstractCRUDService;

@Service
public class TypeService extends AbstractCRUDService<Type> {
    public TypeService(TypeRepository repository) {super(repository);}
}
