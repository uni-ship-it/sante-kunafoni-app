package groupe3.example.santekunafoniapp.DTO;


public class AdministrateurDTO {


    private String nom;

    private String prenom;

    private String email;
    private String tel;
    private String motpass;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMotpass() {
        return motpass;
    }

    public void setMotpass(String motpass) {
        this.motpass = motpass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

}