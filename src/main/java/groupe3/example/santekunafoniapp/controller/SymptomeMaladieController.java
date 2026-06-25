package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeMaladieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Symptôme-Maladie", description = "Gestion des associations entre symptômes et maladies")
@RestController
@RequestMapping("/api/symptome-maladie")
@CrossOrigin(origins = "*")
public class SymptomeMaladieController {

    private final SymptomeMaladieService service;

    public SymptomeMaladieController(SymptomeMaladieService service) {
        this.service = service;
    }

    @Operation(
            summary = "Créer une association symptôme-maladie",
            description = "Associe un symptôme à une maladie."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public SymptomeMaladieDTO ajouter(@RequestBody SymptomeMaladieDTO dto) {
        return service.ajouter(dto);
    }

    @Operation(
            summary = "Lister toutes les associations symptôme-maladie",
            description = "Retourne toutes les associations entre symptômes et maladies."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<SymptomeMaladieDTO> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Récupérer les symptômes d'une maladie",
            description = "Retourne tous les symptômes associés à une maladie donnée."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès"),
            @ApiResponse(responseCode = "404", description = "Maladie non trouvée")
    })
    @GetMapping("/maladie/{idMaladie}")
    public List<SymptomeMaladieDTO> getByMaladie(
            @Parameter(description = "ID de la maladie", required = true)
            @PathVariable Integer idMaladie
    ) {
        return service.getByMaladie(idMaladie);
    }

    @Operation(
            summary = "Récupérer les maladies d'un symptôme",
            description = "Retourne toutes les maladies associées à un symptôme donné."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès"),
            @ApiResponse(responseCode = "404", description = "Symptôme non trouvé")
    })
    @GetMapping("/symptome/{idSymptome}")
    public List<SymptomeMaladieDTO> getBySymptome(
            @Parameter(description = "ID du symptôme", required = true)
            @PathVariable Long idSymptome
    ) {
        return service.getBySymptome(idSymptome);
    }

    @Operation(
            summary = "Supprimer une association symptôme-maladie",
            description = "Supprime l'association entre un symptôme et une maladie."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Association non trouvée")
    })
    @DeleteMapping("/{idSymptome}/{idMaladie}")
    public void supprimer(
            @Parameter(description = "ID du symptôme", required = true)
            @PathVariable Long idSymptome,
            @Parameter(description = "ID de la maladie", required = true)
            @PathVariable Integer idMaladie
    ) {
        service.supprimer(idSymptome, idMaladie);
    }
}