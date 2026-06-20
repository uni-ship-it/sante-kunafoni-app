package groupe3.example.santekunafoniapp.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "agent_sante")
public class AgentSante {

    @Id
    @Column(name = "idUtilisateur")
    private Long idUtilisateur;

    private String specialite;

    private String centre;

    private String email;

    public AgentSante() {
    }

    public AgentSante(Long idUtilisateur,
                      String specialite,
                      String centre,
                      String email) {
        this.idUtilisateur = idUtilisateur;
        this.specialite = specialite;
        this.centre = centre;
        this.email = email;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}