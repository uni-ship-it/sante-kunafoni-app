package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.services.serviceInterface.UtilisateurServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private UtilisateurServiceInterface utilisateurService;

    public UtilisateurController(UtilisateurServiceInterface utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll(){
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateur();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable Long id){
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);

        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UtilisateurDTO uDTO){
        try {
            utilisateurService.addUtilisateur(uDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'utilisateur a été créé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création : " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> uptdate(@PathVariable Long id, @RequestBody UtilisateurDTO uDTAO){
        try {
            utilisateurService.updateUtilisateur(id, uDTAO);
            return ResponseEntity.ok("Utilisateur modifié avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur lors de la modification : " + e.getMessage());
        }

    }

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
