package groupe3.example.santekunafoniapp.DTO;

import lombok.Data;

@Data
public class AdministrateurRequestDTO {

        private String nom;
        private String prenom;
        private String email;
        private String tel;
        private String motpass;
        private String role;
    }

