package yarema.project.toystore.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import yarema.project.toystore.entity.Waybill;

@Service
public class WaybillService extends AbstractCRUDService<Waybill>{
    public WaybillService(JpaRepository<Waybill, Long> repository) { super(repository); }
}
