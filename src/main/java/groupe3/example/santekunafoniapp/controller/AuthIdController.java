package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.dtos.ConnexionRequest;
import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Autorise Angular
public class AuthIdController {

    // Injecte ici ton Service ou Repository (ex: private UtilisateurService utilisateurService;)

    /**
     * 1. ENDPOINT POUR L'INSCRIPTION DU PATIENT
     * Reçoit le JSON envoyé par Angular et le transforme en entité Patient
     */
    @PostMapping("/inscription")
    public ResponseEntity<?> inscriptionPatient(@RequestBody Patient patient) {
        try {
            // 1. (Optionnel) Encoder le mot de passe ici si tu utilises Spring Security
            // 2. Sauvegarder en BDD via ton service : Patient patientSauvegarde = patientService.sauvegarder(patient);

            System.out.println("Nouveau patient inscrit : " + patient.getNom());
            return ResponseEntity.ok(patient); // On renvoie le patient créé
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

}