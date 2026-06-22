package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Administrateur")
@Data
public class Administrateur extends Utilisateur{
    private String email;
    public Administrateur(){}
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
