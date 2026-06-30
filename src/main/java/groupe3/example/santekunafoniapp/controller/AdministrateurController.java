package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.AdministrateurDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Administrateur", description = "Gestion des administrateur")
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    private final AdministrateurServiceInterface administrateurService;

    public AdministrateurController(AdministrateurServiceInterface administrateurService) {
        this.administrateurService = administrateurService;
    }

    @Operation(summary = "Ajouter un Administrateur",
            description = "Enregistrement d'un nouveau administrateur")
    @ApiResponses(
            value = {
        @ApiResponse(responseCode = "201", description = "Administrateur ajouté avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
            }
            )
    @PostMapping
    public String ajouter(@RequestBody AdministrateurDTO dto){
        administrateurService.ajouter(dto);
        return "ajouté avec succès";
    }

    @Operation(summary = "Afficher Administrateur",
            description = "La Liste de tout les administrateurs")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "La liste de l'Administrateur trouvé"),
                    @ApiResponse(responseCode = "404", description = "La liste administrateur introuvable"),
            }
    )
    @GetMapping
    public List<AdministrateurDTO> afficherTous(){
        return administrateurService.afficherTous();
    }

    @Operation(summary = "Afficher Administrateur ",
            description = "Afficher un administrateur a travers son id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Administrateur trouvé"),
                    @ApiResponse(responseCode = "404", description = "Administrateur introuvable"),
            }
    )
    @GetMapping("/{id}")
    public AdministrateurDTO afficherParId(@PathVariable Long id){
         return administrateurService.afficherParId(id);
    }
    @Operation(summary = "Modifier un administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrateur modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Administrateur introuvable")
    })
    @PutMapping("/{id}")
    public String modifier(
            @PathVariable Long id,
            @RequestBody AdministrateurDTO dto){

         administrateurService.modifier(id,dto);
         return "Modification réussie";
    }

    @Operation(summary = "Supprimer un administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrateur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Administrateur introuvable")
    })
    @DeleteMapping("/{id}")
    public String supprimer(@PathVariable Long id){

        administrateurService.supprimer(id);
        return " La Suppression a été effectuer";
    }

}