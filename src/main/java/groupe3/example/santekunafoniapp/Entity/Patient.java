package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class Patient extends Utilisateur {

    private String localite;
    private Long age;
    private String etat;
    private String sexe;
    private Date periode;
}