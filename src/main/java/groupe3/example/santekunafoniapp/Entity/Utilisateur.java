package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utilisateur")
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String tel;
    private String motpass;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Constructeur avec paramètres
    public Utilisateur(Long idUtilisateur, String nom, String prenom, String tel, String motpass, Role role) {
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