package yarema.project.toystore.controller.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yarema.project.toystore.service.AbstractCRUDService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public abstract class AbstractCRUDController<TEntity> {
    final protected AbstractCRUDService<TEntity> service;


    //CREATE ROUTS
    //getTypeName()
    @ApiOperation(value = "Creates entity" )
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TEntity create(@RequestBody TEntity entity) {
        return service.create(entity);
    }

    //READ ROUTS
    @ApiOperation(value = "Returns array of entities" )
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TEntity> getAll(
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        return service.findAll(size,page);
    }
    @ApiOperation(value = "Returns entity by id" )
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TEntity getById(@PathVariable Long id) {
        return service.findById(id);
    }

    //UPDATE ROUTS
    @ApiOperation(value = "Updates entity by id" )
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TEntity update(@PathVariable Long id, @RequestBody TEntity entity) {
        return service.update(id, entity);
    }

    //DELETE ROUTS
    @ApiOperation(value = "Deletes entity by id" )
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
