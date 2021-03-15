package yarema.project.toystore.service.v1;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.repository.WarehouseRepository;
import yarema.project.toystore.service.AbstractCRUDService;

@Service
public class WarehouseService extends AbstractCRUDService<Warehouse> {
    public WarehouseService (WarehouseRepository repository) {super(repository);}
}
