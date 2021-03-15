package yarema.project.toystore.entity;


import lombok.Data;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String name;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "product")
    private List<Waybill> Waybills;
}
