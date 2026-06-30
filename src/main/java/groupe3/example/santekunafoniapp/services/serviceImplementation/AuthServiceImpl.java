package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.LoginRequestDTO;
import groupe3.example.santekunafoniapp.DTO.LoginResponseDTO;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.Repository.UtilisateurRepository;
import groupe3.example.santekunafoniapp.security.JwtService;
import groupe3.example.santekunafoniapp.services.serviceInterface.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        // 1. Chercher utilisateur par téléphone
        Utilisateur user = repository.findByTel(request.getTel())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Utilisateur introuvable"
                ));

        // 2. Vérifier mot de passe
        if (!passwordEncoder.matches(request.getMotpass(), user.getMotpass())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Mot de passe incorrect"
            );
        }

        // 3. Générer token JWT
        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(token, user.getRole().name());
    }
}