package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientServiceInterface patientService;

    public PatientController(PatientServiceInterface patientService) {
        this.patientService = patientService;
    }

    // 1. Ajouter un patient
    @PostMapping
    public Patient ajouterPatient(@RequestBody Patient patient) {
        return patientService.ajouterPatient(patient);
    }

    // 2. Modifier un patient
    @PutMapping("/{id}")
    public Patient modifierPatient(@PathVariable int id,
                                   @RequestBody Patient patient) {
        return patientService.modifierPatient(id, patient);
    }

    // 3. Supprimer un patient
    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable int id) {
        patientService.supprimerPatient(id);
    }

    // 4. Afficher tous les patients
    @GetMapping
    public List<Patient> afficherTousLesPatients() {
        return patientService.afficherTousLesPatients();
    }

    // 5. Afficher un patient par ID
    @GetMapping("/{id}")
    public Patient afficherPatientParId(@PathVariable int id) {
        return patientService.afficherPatientParId(id);
    }
}
