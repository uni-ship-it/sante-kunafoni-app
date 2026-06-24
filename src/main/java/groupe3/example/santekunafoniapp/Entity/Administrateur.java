package groupe3.example.santekunafoniapp.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name="administrateur")
@Data
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class Administrateur extends Utilisateur {

    @Column(unique = true)
    private String email;


    public Administrateur(){

    }

}
