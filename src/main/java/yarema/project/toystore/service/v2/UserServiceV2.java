package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.services.IUserService;
import yarema.project.toystore.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceV2 implements IUserService {
    final ModelMapper modelMapper;
    final UserRepository userRepository;

    @Override
    public List<ResponseUserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseUserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO findById(Long id) throws NotFoundException {
        if(!userRepository.existsById(id)) throw new NotFoundException("User with specified id not found");

        return modelMapper.map(userRepository.findById(id).orElse(null), ResponseUserDTO.class);
    }

    @Override
    public ResponseUserDTO update(Long id, RequestUserDTO dto) throws NotFoundException {
        if(!userRepository.existsById(id)) throw new NotFoundException("User with specified id not found");

        User entity = modelMapper.map(dto, User.class);
        entity.setId(id);
        return modelMapper.map(userRepository.save(entity), ResponseUserDTO.class);
    }

    @Override
    public ResponseUserDTO create(RequestUserDTO dto) {
        User entity = modelMapper.map(dto, User.class);
        return modelMapper.map(userRepository.save(entity), ResponseUserDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!userRepository.existsById(id)) throw new NotFoundException("User with specified id not found");

        userRepository.deleteById(id);
    }
}
