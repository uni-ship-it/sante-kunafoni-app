package groupe3.example.santekunafoniapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomLoginResponse {
    private Long idUtilisateur;
    private String tel;
    private String nom;
    private String role;
}