package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SymptomeMaladieId implements Serializable {

    private Long idSymptome;
    private Long idMaladie;
}