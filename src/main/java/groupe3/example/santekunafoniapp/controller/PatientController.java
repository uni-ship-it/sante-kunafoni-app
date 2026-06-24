package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Patient", description = "Gestion des comptes patient") // Groupe les endpoints
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientServiceInterface patientService;

    public PatientController(PatientServiceInterface patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Créer un patient", description = "Permet de créer un patient")
    @PostMapping
    public Patient ajouterPatient(@RequestBody Patient patient) {
        return patientService.ajouterPatient(patient);
    }

    @Operation(summary = "Modifier un patient", description = "Permet de modifier un patient")
    @PutMapping("/{id}")
    public Patient modifierPatient(@PathVariable Long id,
                                   @RequestBody Patient patient) {
        return patientService.modifierPatient(id, patient);
    }

    @Operation(summary = "Supprimer un patient", description = "Permet de supprimer un patient")
    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable Long id) {
        patientService.supprimerPatient(id);
    }

    @Operation(summary = "Afficher tous les patients", description = "Permet d'afficher un patient")
    @GetMapping
    public List<Patient> afficherTousLesPatients() {
        return patientService.afficherTousLesPatients();
    }

    @Operation(summary = "Récuperer un patient", description = "Permet de récuperer un patient à travers son identifiant")
    @GetMapping("/{id}")
    public Patient afficherPatientParId(@PathVariable Long id) {
        return patientService.afficherPatientParId(id);
    }
}