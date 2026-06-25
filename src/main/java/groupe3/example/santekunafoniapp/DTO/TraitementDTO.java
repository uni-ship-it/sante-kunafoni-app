package groupe3.example.santekunafoniapp.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TraitementDTO {
    private String nomTraitement;
    private LocalDate datedebut;
    private LocalDate datefin;
    private String description;
    private Long idMaladie;
    private Long idUtilisateur;
    private Long idPatient;
}