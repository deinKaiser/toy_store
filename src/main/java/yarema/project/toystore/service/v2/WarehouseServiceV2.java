package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.services.IWarehouseService;
import yarema.project.toystore.repository.WarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceV2 implements IWarehouseService {
    final ModelMapper modelMapper;
    final WarehouseRepository warehouseRepository;

    @Override
    public List<ResponseWarehouseDTO> findAll() {
        List<Warehouse> list = warehouseRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseWarehouseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseWarehouseDTO findById(Long id) throws NotFoundException {
        if(!warehouseRepository.existsById(id)) throw new NotFoundException("Warehouse with specified id not found");

        return modelMapper.map(warehouseRepository.findById(id).orElse(null), ResponseWarehouseDTO.class);
    }

    @Override
    public ResponseWarehouseDTO update(Long id, RequestWarehouseDTO dto) throws NotFoundException {
        if(!warehouseRepository.existsById(id)) throw new NotFoundException("Warehouse with specified id not found");

        Warehouse entity = modelMapper.map(dto, Warehouse.class);
        entity.setId(id);
        return modelMapper.map(warehouseRepository.save(entity), ResponseWarehouseDTO.class);
    }

    @Override
    public ResponseWarehouseDTO create(RequestWarehouseDTO dto) {
        Warehouse entity = modelMapper.map(dto, Warehouse.class);
        return modelMapper.map(warehouseRepository.save(entity), ResponseWarehouseDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!warehouseRepository.existsById(id)) throw new NotFoundException("Warehouse with specified id not found");

        warehouseRepository.deleteById(id);
    }
}
