package groupe3.example.santekunafoniapp.DTO;

import java.util.List;

public class StatistiqueMaladieDTOs {

    /** Un point sur la carte : une localité + sa maladie dominante. */
    public record MaladieLocaliteDTO(
            String localite,
            String maladieDominante,
            long totalCas,
            List<CasMaladieDTO> detail
    ) {}

    /** Nombre de cas d'une maladie dans une localité. */
    public record CasMaladieDTO(String maladie, long cas) {}

    /** Résumé personnel : les 2 compteurs + l'alerte. */
    public record ResumePatientDTO(
            String prenom,
            String localite,
            long nbMaladies,
            long nbSymptomes,
            String alerte
    ) {}
}