package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "agent_sante")
@PrimaryKeyJoinColumn(name = "idUtilisateur")
public class AgentSante extends Utilisateur {

    private String specialite;

    private String centre;

    @Column(unique = true)
    private String email;

    public AgentSante() {
    }

    public AgentSante(String specialite,
                      String centre,
                      String email) {
        this.specialite = specialite;
        this.centre = centre;
        this.email = email;
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