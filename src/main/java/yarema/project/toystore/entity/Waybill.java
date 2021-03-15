package yarema.project.toystore.entity;

import lombok.Data;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;

@Data
@Entity
public class Waybill implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer count;

    private String waybillCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
