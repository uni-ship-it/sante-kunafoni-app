package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.CustomLoginRequest;
import groupe3.example.santekunafoniapp.DTO.CustomLoginResponse;
import groupe3.example.santekunafoniapp.services.serviceInterface.CustomAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/custom-auth")
@CrossOrigin(origins = "http://localhost:4200") // Permet à ton Angular local de communiquer avec Spring

public class CustomAuthController {
    @Autowired
    private CustomAuthService customAuthService;

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody CustomLoginRequest loginRequest) {
        try {
            // On transmet le téléphone et le mot de passe récupérés depuis le formulaire
            CustomLoginResponse response = customAuthService.verifierAuthentification(
                    loginRequest.getTel(),
                    loginRequest.getMotpass()
            );
            // Si tout est bon, on renvoie le profil avec un statut HTTP 200 OK
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Si les identifiants sont faux, on renvoie l'erreur avec un statut 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
