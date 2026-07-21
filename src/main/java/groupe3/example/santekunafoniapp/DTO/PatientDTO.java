
package groupe3.example.santekunafoniapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import groupe3.example.santekunafoniapp.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long idUtilisateur;

    private String nom;
    private String prenom;
    private String tel;

    // Cette annotation dit à Spring : "Si le front t'envoie 'motpass', mets-le ici"
    // Tout en gardant la variable 'motPass' intacte pour le reste du code Java !
    @JsonProperty("motpass")
    private String motPass;

    private String localite;
    private Long age;
    private String etat;
    private String sexe;
    private Date periode;

    private List<Long> idMaladies;
}