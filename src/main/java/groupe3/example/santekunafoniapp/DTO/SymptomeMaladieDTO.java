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

    public static Object builder() {
        return null;
    }

    public Long getIdSymptome() {
            return null;
    }

    public Long getIdMaladie() {
            return null;
    }

    public Object getNom() {
                return null;
    }

    public Object getNomsymptome() {
            return null;
    }
}