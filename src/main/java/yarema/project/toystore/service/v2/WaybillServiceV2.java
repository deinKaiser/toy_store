package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.services.IWaybillService;
import yarema.project.toystore.repository.ProductRepository;
import yarema.project.toystore.repository.UserRepository;
import yarema.project.toystore.repository.WaybillRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaybillServiceV2 implements IWaybillService {
    final ModelMapper modelMapper;
    final WaybillRepository waybillRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;

    @Override
    public List<ResponseWaybillDTO> findAll() {
        List<Waybill> list = waybillRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseWaybillDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseWaybillDTO findById(Long id) throws NotFoundException {
        if(!waybillRepository.existsById(id)) throw new NotFoundException("Waybill with specified id not found");

        return modelMapper.map(waybillRepository.findById(id).orElse(null), ResponseWaybillDTO.class);
    }

    @Override
    public ResponseWaybillDTO update(Long id, RequestWaybillDTO dto) throws NotFoundException {
        if(!waybillRepository.existsById(id)) throw new NotFoundException("Waybill with specified id not found");
        if(!productRepository.existsById(dto.getProductId())) throw new NotFoundException("Product with specified id not found");
        if(!userRepository.existsById(dto.getUserId())) throw new NotFoundException("User with specified id not found");

        Waybill entity = modelMapper.map(dto, Waybill.class);
        //entity.setId(id);
        entity.setProduct(productRepository.getOne(dto.getProductId()));
        entity.setUser(userRepository.getOne(dto.getUserId()));
        return modelMapper.map(waybillRepository.save(entity), ResponseWaybillDTO.class);
    }

    @Override
    public ResponseWaybillDTO create(RequestWaybillDTO dto) throws NotFoundException {
        if(!productRepository.existsById(dto.getProductId())) throw new NotFoundException("Product with specified id not found");
        if(!userRepository.existsById(dto.getUserId())) throw new NotFoundException("User with specified id not found");

        Waybill entity = modelMapper.map(dto, Waybill.class);
        entity.setProduct(productRepository.getOne(dto.getProductId()));
        entity.setUser(userRepository.getOne(dto.getUserId()));
        return modelMapper.map(waybillRepository.save(entity), ResponseWaybillDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!waybillRepository.existsById(id)) throw new NotFoundException("Waybill with specified id not found");

        waybillRepository.deleteById(id);
    }
}
