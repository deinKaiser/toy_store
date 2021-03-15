package yarema.project.toystore.entity;


import lombok.Data;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Type implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type")
    private List<Product> products;
}
