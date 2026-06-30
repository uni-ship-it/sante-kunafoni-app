package groupe3.example.santekunafoniapp.controller;


import groupe3.example.santekunafoniapp.DTO.TraitementDTO;
import groupe3.example.santekunafoniapp.Entity.Traitement;
import groupe3.example.santekunafoniapp.services.serviceInterface.TraitementServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Traitements", description = "Gestion des traitements médicaux")
@RestController
@RequestMapping("/api/traitements")
public class TraitementController {


    private final TraitementServiceInterface traitementService;


    public TraitementController(TraitementServiceInterface traitementService) {

        this.traitementService = traitementService;

    }

    @Operation(
            summary = "Créer un traitement",
            description = "Ajouter un traitement médical"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traitement créé"),
            @ApiResponse(responseCode = "400", description = "Erreur")
    })
    @PostMapping("/add")
    public Traitement add(@RequestBody TraitementDTO dto){

        return traitementService.ajouterTraitement(dto);

    }

    @Operation(
            summary = "Lister les traitements"
    )
    @GetMapping
    public List<Traitement> getAll(){

        return traitementService.getAllTraitements();

    }

    @Operation(
            summary = "Trouver un traitement"
    )
    @GetMapping("/{id}")
    public Traitement getById(@PathVariable Long id){

        return traitementService.getTraitementById(id)
                .orElseThrow(
                        () -> new RuntimeException("Traitement non trouvé")
                );

    }

    @Operation(
            summary = "Modifier un traitement"
    )
    @PutMapping("/{id}")
    public Traitement update(
            @PathVariable Long id,
            @RequestBody TraitementDTO dto
    ){

        return traitementService.modifierTraitement(id,dto);

    }

    @Operation(
            summary = "Supprimer un traitement"
    )
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        traitementService.supprimerTraitement(id);

        return "Traitement supprimé avec succès";

    }


}