package groupe3.example.santekunafoniapp.Controller;

import groupe3.example.santekunafoniapp.Entity.PatientSymptome;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientSymptomeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-symptomes")
@Tag(
        name = "Patient-Symptôme",
        description = "Gestion des symptômes assignés aux patients"
)
// @Tag regroupe tous les endpoints de ce controller sous un même titre dans Swagger UI
public class PatientSymptomeController {

    private final PatientSymptomeServiceInterface service;

    public PatientSymptomeController(PatientSymptomeServiceInterface service) {
        this.service = service;
    }

    @Operation(
            summary = "Assigner un symptôme à un patient",
            description = "Crée une association entre un patient et un symptôme. " +
                    "Retourne une erreur si l'association existe déjà."
    )
    // @Operation : summary = titre court affiché dans la liste
    //              description = explication détaillée visible quand on déplie l'endpoint
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptôme assigné avec succès"),
            @ApiResponse(responseCode = "400", description = "Association déjà existante"),
            @ApiResponse(responseCode = "404", description = "Patient ou symptôme non trouvé")
    })
    // @ApiResponses : documente les codes HTTP possibles — très utile pour les autres devs
    @PostMapping("/{idPatient}/symptomes/{idSymptome}")
    public ResponseEntity<?> ajouterSymptome(
            @Parameter(description = "ID du patient", required = true)
            @PathVariable Long idPatient,

            @Parameter(description = "ID du symptôme", required = true)
            @PathVariable Long idSymptome
    ) {
        try {
            PatientSymptome result = service.ajouterSymptomeAuPatient(idPatient, idSymptome);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Lister les symptômes d'un patient",
            description = "Retourne tous les symptômes associés à un patient donné."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @GetMapping("/{idPatient}/symptomes")
    public ResponseEntity<List<PatientSymptome>> getSymptomes(
            @Parameter(description = "ID du patient", required = true)
            @PathVariable Long idPatient
    ) {
        return ResponseEntity.ok(service.getSymptomesByPatient(idPatient));
    }

    @Operation(
            summary = "Supprimer un symptôme d'un patient",
            description = "Supprime l'association entre un patient et un symptôme."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association supprimée"),
            @ApiResponse(responseCode = "404", description = "Association non trouvée")
    })
    @DeleteMapping("/{idPatient}/symptomes/{idSymptome}")
    public ResponseEntity<String> supprimerSymptome(
            @Parameter(description = "ID du patient", required = true)
            @PathVariable Long idPatient,

            @Parameter(description = "ID du symptôme", required = true)
            @PathVariable Long idSymptome
    ) {
        try {
            service.supprimerSymptomeDuPatient(idPatient, idSymptome);
            return ResponseEntity.ok("Symptôme supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Lister toutes les associations patient-symptôme")
    @GetMapping
    public ResponseEntity<List<PatientSymptome>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}