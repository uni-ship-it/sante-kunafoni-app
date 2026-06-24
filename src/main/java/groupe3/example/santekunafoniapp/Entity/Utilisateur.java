package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Entity.Notification;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String tel;
    private String motpass;

    }

    // Constructeur avec paramètres
    public Utilisateur(Long idUtilisateur, String nom, String prenom, String tel, String motpass, Role role){
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.motpass = motpass;
        this.role = role;
    }

    //@OneToMany
    //List<Notification> notifications;
}
