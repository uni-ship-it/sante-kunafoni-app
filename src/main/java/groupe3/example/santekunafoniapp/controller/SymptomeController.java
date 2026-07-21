package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Symptômes", description = "Gestion des symptômes")
@RestController
@RequestMapping("/api/symptomes")
public class SymptomeController {

    private final SymptomeServiceInterface service;

    public SymptomeController(SymptomeServiceInterface service) {
        this.service = service;
    }

    @Operation(
            summary = "Lister tous les symptômes",
            description = "Retourne la liste complète de tous les symptômes enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Symptome> getAll() {
        return service.getAllSymptome();
    }

    @Operation(
            summary = "Récupérer un symptôme par ID",
            description = "Retourne les détails d'un symptôme à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptôme trouvé"),
            @ApiResponse(responseCode = "404", description = "Symptôme non trouvé")
    })
    @GetMapping("/{id}")
    public Symptome getById(
            @Parameter(description = "ID du symptôme", required = true)
            @PathVariable Long id
    ) {
        return service.getSymptomeById(id);
    }

    @Operation(
            summary = "Créer un symptôme",
            description = "Enregistre un nouveau symptôme dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptôme créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Symptome createSymptome(@RequestBody Symptome symptome) {
        return service.createSymptome(symptome);
    }

    @Operation(
            summary = "Modifier un symptôme",
            description = "Met à jour les informations d'un symptôme existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptôme modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Symptôme non trouvé")
    })
    @PutMapping("/{id}")
    public Symptome updateById(
            @Parameter(description = "ID du symptôme à modifier", required = true)
            @PathVariable Long id,
            @RequestBody Symptome symptome
    ) {
        return service.updateSymptomeById(id, symptome);
    }

    @Operation(
            summary = "Supprimer un symptôme",
            description = "Supprime définitivement un symptôme à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptôme supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Symptôme non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "ID du symptôme à supprimer", required = true)
            @PathVariable Long id
    ) {
        service.deleteSymptomeById(id);
        // ✅ Appel du service décommenté — assure-toi que deleteSymptomeById existe dans l'interface
    }
}