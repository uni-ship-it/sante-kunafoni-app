package groupe3.example.santekunafoniapp.DTO;

public class CustomLoginRequest {
  private String tel;
  private String motpass;

  // Constructeur vide nécessaire pour Spring Boot
  public CustomLoginRequest() {
  }

  // Constructeur avec paramètres
  public CustomLoginRequest(String tel, String motpass) {
    this.tel = tel;
    this.motpass = motpass;
  }

  // Getter pour Tel
  public String getTel() {
    return tel;
  }

  // Setter pour Tel
  public void setTel(String tel) {
    this.tel = tel;
  }

  // Getter pour Motpass
  public String getMotpass() {
    return motpass;
  }

  // Setter pour Motpass
  public void setMotpass(String motpass) {
    this.motpass = motpass;
  }
}