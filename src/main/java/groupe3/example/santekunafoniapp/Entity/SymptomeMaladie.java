package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "symptome_maladie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SymptomeMaladie {

    @EmbeddedId
    private SymptomeMaladieId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSymptome")
    @JoinColumn(name = "idSymptome", nullable = false)
    private Symptome symptome;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMaladie")
    @JoinColumn(name = "idMaladie", nullable = false)
    private Maladie maladie;
}