package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.Notification;
import java.util.List;
import groupe3.example.santekunafoniapp.Entity.Role;

@Entity
@Data
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String tel;
    private String motpass;
    private Role role;

    // Constructeur sans paramètres
    public Utilisateur(){

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

    @OneToMany
    List<Notification> notifications;
}
