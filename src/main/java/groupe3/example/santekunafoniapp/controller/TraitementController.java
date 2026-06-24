package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.TraitementDTO;
import groupe3.example.santekunafoniapp.Entity.Traitement;
import groupe3.example.santekunafoniapp.services.serviceImplementation.TraitementServiceImplementation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(name = "Traitement", description = "Gestion des traitements !")
@RestController
@RequestMapping("/api/traitements")
public class TraitementController {

    private final TraitementServiceImplementation service;

    public TraitementController(TraitementServiceImplementation service) {
        this.service = service;
    }
    @Operation(summary = "Créer traitement", description = "Cette methode permet de créer un nouveau traitement !")
    @PostMapping
    public Traitement ajouter(@RequestBody TraitementDTO traitementDTO) {
        return service.ajouterTraitement(traitementDTO);
    }

    @Operation(summary = "Recuperer les traitements", description = "Cette methode permet de recuperer tous les traitement !")
    @GetMapping
    public List<Traitement> liste() {
        return service.getAllTraitements();
    }

    @Operation(summary = "Recuperer un traitement", description = "Cette methode permet de recuperer un traitement à travers son identifiant !")
    @GetMapping("/{id}")
    public Traitement trouver(@PathVariable Long id) {
        return service.getTraitementById(id)
                .orElseThrow(() -> new RuntimeException("Traitement non trouvé"));
    }

    @Operation(summary = "Modifier un traitement", description = "Cette methode permet de modifier un traitement spécifique !")
    @PutMapping("/{id}")
    public Traitement modifier(@PathVariable Long id, @RequestBody TraitementDTO traitementDTO) {
        return service.modifierTraitement(id, traitementDTO);
    }

    @Operation(summary = "Supprimer un traitement", description = "Cette methode permet de supprimer un traitement !")
    @DeleteMapping("/{id}")
    public String supprimer(@PathVariable Long id) {
        service.supprimerTraitement(id);
        return "Traitement supprimé avec succès";
    }
}