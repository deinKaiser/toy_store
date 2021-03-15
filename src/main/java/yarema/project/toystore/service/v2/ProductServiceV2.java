package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.services.IProductService;
import yarema.project.toystore.repository.ProductRepository;
import yarema.project.toystore.repository.TypeRepository;
import yarema.project.toystore.repository.WarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceV2 implements IProductService {
    final ModelMapper modelMapper;
    final ProductRepository productRepository;
    final TypeRepository typeRepository;
    final WarehouseRepository warehouseRepository;

    @Override
    public List<ResponseProductDTO> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseProductDTO findById(Long id) throws NotFoundException {
        if(!productRepository.existsById(id)) throw new NotFoundException("Product with specified id not found");

        return modelMapper.map(productRepository.findById(id).orElse(null), ResponseProductDTO.class);
    }

    @Override
    public ResponseProductDTO update(Long id, RequestProductDTO dto) throws NotFoundException {
        if(!productRepository.existsById(id)) throw new NotFoundException("Product with specified id not found");
        if(!typeRepository.existsById(dto.getTypeId())) throw new NotFoundException("Type with specified id not found");
        if(!warehouseRepository.existsById(dto.getWarehouseId())) throw new NotFoundException("Warehouse with specified id not found");

        Product entity = modelMapper.map(dto, Product.class);
        //entity.setId(id);
        entity.setType(typeRepository.getOne(dto.getTypeId()));
        entity.setWarehouse(warehouseRepository.getOne(dto.getWarehouseId()));
        return modelMapper.map(productRepository.save(entity), ResponseProductDTO.class);
    }

    @Override
    public ResponseProductDTO create(RequestProductDTO dto) throws NotFoundException {
        if(!typeRepository.existsById(dto.getTypeId())) throw new NotFoundException("Type with specified id not found");
        if(!warehouseRepository.existsById(dto.getWarehouseId())) throw new NotFoundException("Warehouse with specified id not found");

        Product entity = modelMapper.map(dto, Product.class);
        entity.setType(typeRepository.getOne(dto.getTypeId()));
        entity.setWarehouse(warehouseRepository.getOne(dto.getWarehouseId()));
        return modelMapper.map(productRepository.save(entity), ResponseProductDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!productRepository.existsById(id)) throw new NotFoundException("Product with specified id not found");

        productRepository.deleteById(id);
    }
}
