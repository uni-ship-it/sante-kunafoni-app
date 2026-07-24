package groupe3.example.santekunafoniapp.controller;
import groupe3.example.santekunafoniapp.DTO.PatientDTO;
import groupe3.example.santekunafoniapp.Entity.Maladie;
import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Repository.MaladieRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(name = "Patients", description = "Gestion des comptes patients")
@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:4200") // <--- SUPPRIMÉ pour éviter le conflit avec SecurityConfig
public class PatientController {

    private final PatientServiceInterface patientService;

    @Autowired
    private MaladieRepository maladieRepository;

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
    public Patient ajouterPatient(@RequestBody PatientDTO patientDTO) {
        System.out.println("mot de passe: "+ patientDTO.getMotPass());
        System.out.println("NOM: "+ patientDTO.getNom());
        System.out.println("prenom: "+ patientDTO.getPrenom());
        Patient patient = new Patient();
        patient.setMotpass(patientDTO.getMotPass());
        patient.setNom(patientDTO.getNom());
        patient.setPrenom(patientDTO.getPrenom());
        patient.setLocalite(patientDTO.getLocalite());
        patient.setPeriode(patientDTO.getPeriode());
        patient.setAge(patientDTO.getAge());
        patient.setEtat(patientDTO.getEtat());
        patient.setTel(patientDTO.getTel());
        patient.setRole(Role.PATIENT);
        patient.setSexe(patientDTO.getSexe());

        System.out.println("patient mot de passe: "+ patient.getMotpass());

        // GESTION SÉCURISÉE DES MALADIES
        Set<Maladie> maladies = new HashSet<>();
        if (patientDTO.getIdMaladies() != null && !patientDTO.getIdMaladies().isEmpty()) {
            maladies.addAll(maladieRepository.findAllById(patientDTO.getIdMaladies()));
        }
        patient.setMaladies(maladies);

        return patientService.ajouterPatient(patient);
    }

    @Operation(summary = "Modifier un patient", description = "Met à jour les informations d'un patient existant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @PutMapping("/{id}")
    public Patient modifierPatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.modifierPatient(id, patient);
    }

    @Operation(summary = "Supprimer un patient", description = "Supprime définitivement un patient.")
    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable Long id) {
        patientService.supprimerPatient(id);
    }

    @Operation(summary = "Lister tous les patients")
    @GetMapping
    public List<Patient> afficherTousLesPatients() {
        return patientService.afficherTousLesPatients();
    }

    @Operation(summary = "Récupérer un patient par ID")
    @GetMapping("/{id}")
    public Patient afficherPatientParId(@PathVariable Long id) {
        return patientService.afficherPatientParId(id);
    }
    //  URL correspondante au Service Angular : /api/patients/derniers

    @GetMapping("/derniers")
    public List<Patient> getDerniersPatients() {
        return patientService.getDerniersPatients();
    }

    //  URL correspondante au Service Angular : /api/patients/count
    @GetMapping("/count")
    public Long nombrePatients() {
        return patientService.nombrePatients();
    }

    //  URL correspondante au Service Angular : /api/patients/hommes
    @GetMapping("/hommes")
    public long compterHommes() {
        return patientService.compterHommes();
    }

    //  URL correspondante au Service Angular : /api/patients/femmes
    @GetMapping("/femmes")
    public long compterFemmes() {
        return patientService.compterFemmes();
    }
}