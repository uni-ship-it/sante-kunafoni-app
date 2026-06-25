package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Patients", description = "Gestion des comptes patients")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientServiceInterface patientService;

    public PatientController(PatientServiceInterface patientService) {
        this.patientService = patientService;
    }

    @Operation(
            summary = "Créer un patient",
            description = "Enregistre un nouveau patient dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Patient ajouterPatient(@RequestBody Patient patient) {
        return patientService.ajouterPatient(patient);
    }

    @Operation(
            summary = "Modifier un patient",
            description = "Met à jour les informations d'un patient existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @PutMapping("/{id}")
    public Patient modifierPatient(
            @Parameter(description = "ID du patient à modifier", required = true)
            @PathVariable Long id,
            @RequestBody Patient patient
    ) {
        return patientService.modifierPatient(id, patient);
    }

    @Operation(
            summary = "Supprimer un patient",
            description = "Supprime définitivement un patient à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @DeleteMapping("/{id}")
    public void supprimerPatient(
            @Parameter(description = "ID du patient à supprimer", required = true)
            @PathVariable Long id
    ) {
        patientService.supprimerPatient(id);
    }

    @Operation(
            summary = "Lister tous les patients",
            description = "Retourne la liste complète de tous les patients enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Patient> afficherTousLesPatients() {
        return patientService.afficherTousLesPatients();
    }

    @Operation(
            summary = "Récupérer un patient par ID",
            description = "Retourne les détails d'un patient à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient trouvé"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @GetMapping("/{id}")
    public Patient afficherPatientParId(
            @Parameter(description = "ID du patient", required = true)
            @PathVariable Long id
    ) {
        return patientService.afficherPatientParId(id);
    }
}