package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Administrateurs", description = "Gestion des comptes administrateurs")
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    private final AdministrateurServiceInterface administrateurServiceInterface;

    public AdministrateurController(AdministrateurServiceInterface administrateurServiceInterface) {
        this.administrateurServiceInterface = administrateurServiceInterface;
        // ✅ @Autowired retiré : inutile quand il y a un constructeur, Spring injecte automatiquement
    }

    @Operation(
            summary = "Créer un administrateur",
            description = "Crée un nouveau compte administrateur à partir des données fournies."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public UtilisateurDTO ajouter(@RequestBody UtilisateurDTO dto) {
        return administrateurServiceInterface.ajouterUtilisateur(dto);
    }

    @Operation(
            summary = "Lister tous les administrateurs",
            description = "Retourne la liste complète de tous les administrateurs enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<UtilisateurDTO> afficherTous() {
        return administrateurServiceInterface.afficherUtilisateurs();
    }

    @Operation(
            summary = "Récupérer un administrateur par ID",
            description = "Retourne les détails d'un administrateur à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrateur trouvé"),
            @ApiResponse(responseCode = "404", description = "Administrateur non trouvé")
    })
    @GetMapping("/{id}")
    public UtilisateurDTO afficherParId(
            @Parameter(description = "ID de l'administrateur", required = true)
            @PathVariable Long id
    ) {
        return administrateurServiceInterface.afficherUtilisateurParId(id);
    }

    @Operation(
            summary = "Modifier un administrateur",
            description = "Met à jour les informations d'un administrateur existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrateur modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Administrateur non trouvé")
    })
    @PutMapping("/{id}")
    public UtilisateurDTO modifier(
            @Parameter(description = "ID de l'administrateur à modifier", required = true)
            @PathVariable Long id,
            @RequestBody UtilisateurDTO dto
    ) {
        return administrateurServiceInterface.modifierUtilisateur(id, dto);
    }

    @Operation(
            summary = "Supprimer un administrateur",
            description = "Supprime définitivement un administrateur à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrateur supprimé"),
            @ApiResponse(responseCode = "404", description = "Administrateur non trouvé")
    })
    @DeleteMapping("/{id}")
    public void supprimer(
            @Parameter(description = "ID de l'administrateur à supprimer", required = true)
            @PathVariable Long id
    ) {
        administrateurServiceInterface.supprimerUtilisateur(id);
    }
}