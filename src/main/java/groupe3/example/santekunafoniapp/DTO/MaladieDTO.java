package groupe3.example.santekunafoniapp.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MaladieDTO {
    private Long idMaladie;
    private String nom;
    private String description;
    private LocalDate dateDeclaration;
}