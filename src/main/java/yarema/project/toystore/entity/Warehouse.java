package yarema.project.toystore.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String adress;

    private String phone;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;
}
