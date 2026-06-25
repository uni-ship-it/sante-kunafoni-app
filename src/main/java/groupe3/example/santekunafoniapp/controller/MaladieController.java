package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.MaladieDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.MaladieServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Maladies", description = "Gestion des maladies")
@RestController
@RequestMapping("/api/maladies")
public class MaladieController {

    private final MaladieServiceInterface maladieServiceInterface;

    // ✅ @Autowired retiré : injection par constructeur suffisante
    public MaladieController(MaladieServiceInterface maladieServiceInterface) {
        this.maladieServiceInterface = maladieServiceInterface;
    }

    @Operation(
            summary = "Créer une maladie",
            description = "Enregistre une nouvelle maladie dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maladie créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<MaladieDTO> createMaladie(@RequestBody MaladieDTO maladieDTO) {
        return new ResponseEntity<>(maladieServiceInterface.createMaladie(maladieDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Récupérer une maladie par ID",
            description = "Retourne les détails d'une maladie à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maladie trouvée"),
            @ApiResponse(responseCode = "404", description = "Maladie non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MaladieDTO> getMaladieById(
            @Parameter(description = "ID de la maladie", required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(maladieServiceInterface.getMaladieById(id));
    }

    @Operation(
            summary = "Lister toutes les maladies",
            description = "Retourne la liste complète de toutes les maladies enregistrées."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public ResponseEntity<List<MaladieDTO>> getAllMaladies() {
        return ResponseEntity.ok(maladieServiceInterface.getAllMaladies());
    }

    @Operation(
            summary = "Modifier une maladie",
            description = "Met à jour les informations d'une maladie existante."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maladie modifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Maladie non trouvée")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MaladieDTO> updateMaladie(
            @Parameter(description = "ID de la maladie à modifier", required = true)
            @PathVariable Long id,
            @RequestBody MaladieDTO maladieDTO
    ) {
        return ResponseEntity.ok(maladieServiceInterface.updateMaladie(id, maladieDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MaladieDTO> partialUpdateMaladie(@PathVariable("id") Long id, @RequestBody MaladieDTO maladieDTO) {
        //  Nom modifié ici pour éviter la duplication + appel à une méthode "patch" du service
        MaladieDTO updatedMaladie = maladieServiceInterface.partialUpdateMaladie(id, maladieDTO);
        return new ResponseEntity<>(updatedMaladie, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaladie(
            @Parameter(description = "ID de la maladie à supprimer", required = true)
            @PathVariable Long id
    ) {
        maladieServiceInterface.deleteMaladie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}