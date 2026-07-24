package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.LoginRequestDTO;
import groupe3.example.santekunafoniapp.DTO.LoginResponseDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

// <-- AJOUTEZ CETTE LIGNE
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping("/hash")
    public String hash(@RequestParam String motpass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(motpass);   // ← n'importe quel mot de passe, donné directement dans l'URL
    }
}
