package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.services.serviceInterface.UtilisateurServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Utilisateurs", description = "Gestion des comptes utilisateurs")
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurServiceInterface utilisateurService;

    public UtilisateurController(UtilisateurServiceInterface utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Operation(
            summary = "Lister tous les utilisateurs",
            description = "Retourne la liste complète de tous les utilisateurs enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateur());
    }

    @Operation(
            summary = "Récupérer un utilisateur par ID",
            description = "Retourne les détails d'un utilisateur à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(
            @Parameter(description = "ID de l'utilisateur", required = true)
            @PathVariable Long id
    ) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(
            summary = "Créer un utilisateur",
            description = "Enregistre un nouvel utilisateur dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<String> add(@RequestBody UtilisateurDTO uDTO) {
        try {
            utilisateurService.addUtilisateur(uDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'utilisateur a été créé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création : " + e.getMessage());
        }
    }

    @Operation(
            summary = "Modifier un utilisateur",
            description = "Met à jour les informations d'un utilisateur existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @Parameter(description = "ID de l'utilisateur à modifier", required = true)
            @PathVariable Long id,
            @RequestBody UtilisateurDTO uDTO
    ) {
        try {
            utilisateurService.updateUtilisateur(id, uDTO);
            return ResponseEntity.ok("Utilisateur modifié avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : " + e.getMessage());
        }
    }

    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime définitivement un utilisateur à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID de l'utilisateur à supprimer", required = true)
            @PathVariable Long id
    ) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : " + e.getMessage());
        }
    }
}