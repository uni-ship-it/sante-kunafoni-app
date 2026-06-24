package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Administrateur")
@Data
@EqualsAndHashCode(callSuper = true)
public class Administrateur extends Utilisateur{
    private String email;
    public Administrateur(){}

    public void setEmail(String email) {
        this.email = email;
    }
}
