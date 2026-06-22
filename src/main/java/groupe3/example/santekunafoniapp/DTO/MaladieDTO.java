package groupe3.example.santekunafoniapp.DTO;

import lombok.Data;
import java.time.LocalDate; // Changement de l'import ici

@Data
public class MaladieDTO {
    private Long idMaladie;
    private String nom;
    private String description;
    private LocalDate dateDeclaration;

    public Object getDescription() {
            return null;
    }

    public Object getDateDeclaration() {
                return null;
    }

    public Object getNom() {
            return null;
    }
}