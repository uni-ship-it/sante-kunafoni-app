package groupe3.example.santekunafoniapp.DTO;

import groupe3.example.santekunafoniapp.Entity.Role;
import lombok.Data;

import java.util.Date;

@Data
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
}
