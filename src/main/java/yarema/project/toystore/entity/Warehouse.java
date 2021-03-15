package yarema.project.toystore.entity;


import lombok.Data;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Warehouse implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String adress;

    private String phone;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;
}
