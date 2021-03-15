package yarema.project.toystore.interfaces;

import javassist.NotFoundException;

import java.util.List;

public interface IDTOService<TRequestDTO extends IDTO, TResponseDTO extends IDTO> {
    List<TResponseDTO> findAll();
    TResponseDTO findById(Long id) throws NotFoundException;
    TResponseDTO update(Long id, TRequestDTO dto) throws NotFoundException;
    TResponseDTO create(TRequestDTO dto) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
}
