package groupe3.example.santekunafoniapp.DTO;
import groupe3.example.santekunafoniapp.Entity.Role;
import lombok.Data;


@Data
public class UtilisateurDTO {
    private String nom;
    private String Prenom;
    private String tel;
    private String motPass;
    private Role role;
}
