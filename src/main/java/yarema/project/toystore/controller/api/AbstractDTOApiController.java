package yarema.project.toystore.controller.api;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yarema.project.toystore.interfaces.IDTO;
import yarema.project.toystore.interfaces.IDTOService;
import yarema.project.toystore.interfaces.IEntity;

import java.util.List;

@RequiredArgsConstructor
@RestController
public abstract class AbstractDTOApiController<TEntity extends IEntity, TRequestDTO extends IDTO, TResponseDTO extends IDTO> {
    final IDTOService<TRequestDTO, TResponseDTO> service;

    @ApiOperation(value = "Get list of entities")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TResponseDTO> getAll(
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        List<TResponseDTO> list = service.findAll();
        final int listSize = list.size();
        int start = 0;
        int end = listSize;
        if(listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        return list.subList(start, end);
    }

    @ApiOperation(value = "Get single entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TResponseDTO getById(@PathVariable Long id) throws NotFoundException {
        return service.findById(id);
    }

    @ApiOperation(value = "Delete entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        service.deleteById(id);
    }

    @ApiOperation(value = "Update entity by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TResponseDTO update(@PathVariable Long id, @RequestBody TRequestDTO dto) throws NotFoundException {
        return service.update(id, dto);
    }

    @ApiOperation(value = "Create entity")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TResponseDTO create(@RequestBody TRequestDTO dto) throws NotFoundException {
        return service.create(dto);
    }
}
