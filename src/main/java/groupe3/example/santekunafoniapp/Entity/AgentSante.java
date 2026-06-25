package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "agent_sante")
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class AgentSante extends Utilisateur {

    private String specialite;
    private String centre;

    @Column(unique = true)
    private String email;
}