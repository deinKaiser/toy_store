package yarema.project.toystore.service;

import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.repository.WarehouseRepository;

@Service
public class WarehouseService extends AbstractCRUDService<Warehouse>{
    public WarehouseService (WarehouseRepository repository) {super(repository);}
}
