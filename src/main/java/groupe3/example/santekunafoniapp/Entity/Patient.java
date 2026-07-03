package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class Patient extends Utilisateur {

    @ManyToMany
    @JoinTable(
            name = "patient_maladie",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_maladie")
    )
    private Set<Maladie> maladies = new HashSet<>();

    private String localite;
    private Long age;
    private String etat;
    private String sexe;
    private Date periode;
}