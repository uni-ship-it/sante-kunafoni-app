package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.PatientSymptome;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientSymptomeServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-symptome")
public class PatientSymptomeController {

    private final PatientSymptomeServiceInterface service;

    public PatientSymptomeController(PatientSymptomeServiceInterface service) {
        this.service = service;
    }

    // Assigner un symptôme à un patient
    @PostMapping
    public PatientSymptome ajouterSymptomeAuPatient(
            @RequestParam Long idPatient,
            @RequestParam Long idSymptome) {
        return service.ajouterSymptomeAuPatient(idPatient, idSymptome);
    }

    // Tous les symptômes d'un patient
    @GetMapping("/patient/{patientId}")
    public List<PatientSymptome> getSymptomesByPatient(
            @PathVariable Long patientId) {
        return service.getSymptomesByPatient(patientId);
    }

    // Tous les patients ayant un symptôme donné
    @GetMapping("/symptome/{symptomeId}")
    public List<PatientSymptome> getPatientsBySymptome(
            @PathVariable Long symptomeId) {
        return service.getPatientsBySymptome(symptomeId);
    }

    // Toutes les associations
    @GetMapping
    public List<PatientSymptome> getAll() {
        return service.getAll();
    }

    // Supprimer l'association patient-symptôme
    @DeleteMapping
    public String supprimerSymptomeDuPatient(
            @RequestParam Long idPatient,
            @RequestParam Long idSymptome) {
        service.supprimerSymptomeDuPatient(idPatient, idSymptome);
        return "Association patient-symptôme supprimée avec succès";
    }
}