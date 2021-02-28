package yarema.project.toystore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCRUDService<TEntity> {
    final protected JpaRepository<TEntity, Long> repository;

    //CREATE methods

    public TEntity create(TEntity entity) { return repository.save(entity); }

    //READ methods

    public TEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<TEntity> findAll(Integer size, Integer page) {
        List<TEntity> entityList = repository.findAll();
        if(size == null || page == null || entityList.size() < size){
            return entityList;
        }
        else{
            int start = Math.min(entityList.size(), size * (page - 1));
            int end = Math.min(entityList.size(), page * size);
            return entityList.subList(start, end);
        }
    }

    //UPDATE methods

    public TEntity update(Long id, TEntity entity) {
        return repository.existsById(id) ? repository.save(entity) : null;
    }

    //DELETE methods

    public void deleteById(Long id) { repository.deleteById(id); }
}
