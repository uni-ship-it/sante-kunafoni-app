package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.Date;

@Entity
@Data
@Table(name="patient")
@EqualsAndHashCode(callSuper = true)
public class Patient extends Utilisateur {
    private String localite;
    private Long age;
    private String etat;
    private String sexe;
    private Date periode;
}
