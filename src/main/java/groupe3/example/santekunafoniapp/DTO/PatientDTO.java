package groupe3.example.santekunafoniapp.DTO;

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
    private String nom;
    private String prenom;
    private String tel;
    private String motPass;
    private String localite;
    private Long age;
    private String etat;
    private String sexe;
    private Date periode;

    private List<Long> idMaladies;
}
