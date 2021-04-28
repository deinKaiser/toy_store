package yarema.project.toystore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yarema.project.toystore.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Waybill> waybills;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

