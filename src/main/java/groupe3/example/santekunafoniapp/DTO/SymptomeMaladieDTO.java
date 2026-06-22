package groupe3.example.santekunafoniapp.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SymptomeMaladieDTO {
    private Long idSymptome;
    private Integer idMaladie;
    private String nomSymptome;
    private String nomMaladie;
}