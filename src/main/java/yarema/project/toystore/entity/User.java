package yarema.project.toystore.entity;

import lombok.Data;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
public class User implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Waybill> waybills;
}

