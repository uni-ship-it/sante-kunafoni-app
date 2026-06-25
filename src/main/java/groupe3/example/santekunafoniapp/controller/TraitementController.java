package groupe3.example.santekunafoniapp.controller;


import groupe3.example.santekunafoniapp.Entity.Traitement;
import groupe3.example.santekunafoniapp.services.serviceImplementation.TraitementServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Traitements", description = "Gestion des traitements médicaux")
@RestController
@RequestMapping("/api/traitements")
public class TraitementController {

    private final TraitementServiceImplementation service;

    public TraitementController(TraitementServiceImplementation service) {
        this.service = service;
    }

    @Operation(
            summary = "Créer un traitement",
            description = "Enregistre un nouveau traitement médical dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traitement créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Traitement ajouter(@RequestBody Traitement traitement) {
        return service.ajouterTraitement(traitement);
    }

    @Operation(
            summary = "Lister tous les traitements",
            description = "Retourne la liste complète de tous les traitements enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Traitement> liste() {
        return service.getAllTraitements();
    }

    @Operation(
            summary = "Récupérer un traitement par ID",
            description = "Retourne les détails d'un traitement à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traitement trouvé"),
            @ApiResponse(responseCode = "404", description = "Traitement non trouvé")
    })
    @GetMapping("/{id}")
    public Traitement trouver(
            @Parameter(description = "ID du traitement", required = true)
            @PathVariable Long id
    ) {
        return service.getTraitementById(id)
                .orElseThrow(() -> new RuntimeException("Traitement non trouvé"));
    }
    @Operation(summary = "Modifier un traitement", description = "Cette methode permet de modifier un traitement spécifique !")
    @PutMapping("/{id}")
    public Traitement modifier(
            @Parameter(description = "ID du traitement à modifier", required = true)
            @PathVariable Long id,
            @RequestBody Traitement traitement
    ) {
        return service.modifierTraitement(id, traitement);
    }
    @Operation(summary = "Supprimer un traitement", description = "Cette methode permet de supprimer un traitement !")
    @DeleteMapping("/{id}")
    public String supprimer(
            @Parameter(description = "ID du traitement à supprimer", required = true)
            @PathVariable Long id
    ) {
        service.supprimerTraitement(id);
        return "Traitement supprimé avec succès";
    }
}