package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.services.serviceInterface.UtilisateurServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "Utilisateurs", description = "Gestion des comptes utilisateurs") // Groupe les endpoints
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private UtilisateurServiceInterface utilisateurService;

    public UtilisateurController(UtilisateurServiceInterface utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @Operation(summary = "Recupérer tous les utilisateurs", description = "Permet de recupérer tous les utilisateurs")
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll(){
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateur();
        return ResponseEntity.ok(utilisateurs);
    }

    @Operation(summary = "Recuperer un utilisateur", description = "Permet de recuperer un utilisateur à travers son identifiant")
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable Long id){
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);

        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Créer un utilisateur", description = "Permet de créer un nouveau utilisateur")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UtilisateurDTO uDTO){
        try {
            utilisateurService.addUtilisateur(uDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'utilisateur a été créé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création : " + e.getMessage());
        }
    }

    @Operation(summary = "Modifier un utilisateur", description = "Permet de modifier un utilisateur spécifique à travers son identifiant")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> uptdate(@PathVariable Long id, @RequestBody UtilisateurDTO uDTAO){
        try {
            utilisateurService.updateUtilisateur(id, uDTAO);
            return ResponseEntity.ok("Utilisateur modifié avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur lors de la modification : " + e.getMessage());
        }

    }

    @Operation(summary = "Supprimer un utilisateur", description = "Permet de supprimer un utilisateur à travers son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès ! ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }

    }
}
