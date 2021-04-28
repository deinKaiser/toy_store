package yarema.project.toystore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private ERole name;

    public enum ERole{
        ROLE_USER,
        ROLE_ADMIN
    }
}