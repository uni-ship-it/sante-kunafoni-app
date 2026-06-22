package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymptomeMaladieId implements Serializable {

    private Integer idSymptome;
    private Integer idMaladie;
}