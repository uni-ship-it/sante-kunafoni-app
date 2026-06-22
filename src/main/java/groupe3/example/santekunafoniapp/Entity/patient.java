package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class patient {
    @Id
    private int idUtilisateur;
    private String localite;
    private int age;
    private String etat;
    private String sexe;
    private Date periode;
}
