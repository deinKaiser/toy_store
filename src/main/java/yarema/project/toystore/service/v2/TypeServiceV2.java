package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.services.ITypeService;
import yarema.project.toystore.repository.TypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeServiceV2 implements ITypeService {
    final ModelMapper modelMapper;
    final TypeRepository typeRepository;


    @Override
    public List<ResponseTypeDTO> findAll() {
        List<Type> list = typeRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseTypeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseTypeDTO findById(Long id) throws NotFoundException {
        if(!typeRepository.existsById(id)) throw new NotFoundException("Type with specified id not found");

        return modelMapper.map(typeRepository.findById(id).orElse(null), ResponseTypeDTO.class);
    }

    @Override
    public ResponseTypeDTO update(Long id, RequestTypeDTO dto) throws NotFoundException {
        if(!typeRepository.existsById(id)) throw new NotFoundException("Type with specified id not found");

        Type entity = modelMapper.map(dto, Type.class);
        entity.setId(id);
        return modelMapper.map(typeRepository.save(entity), ResponseTypeDTO.class);
    }

    @Override
    public ResponseTypeDTO create(RequestTypeDTO dto) {
        Type entity = modelMapper.map(dto, Type.class);
        return modelMapper.map(typeRepository.save(entity), ResponseTypeDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!typeRepository.existsById(id)) throw new NotFoundException("Type with specified id not found");

        typeRepository.deleteById(id);
    }
}
