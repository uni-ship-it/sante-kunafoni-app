package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.MaladieDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.MaladieServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Maladie", description = "Gestion des maladies")
@RestController
@RequestMapping("/api/maladies")
public class MaladieController {

    @Autowired
    private MaladieServiceInterface maladieServiceInterface;
    @Operation(summary = "Créer une maladie", description = "Cette methode permet de créer une maladie !")
    @PostMapping
    public ResponseEntity<MaladieDTO> createMaladie(@RequestBody MaladieDTO maladieDTO) {
        // Aligné sur ton interface : createMaladie
        MaladieDTO savedMaladie = maladieServiceInterface.createMaladie(maladieDTO);
        return new ResponseEntity<>(savedMaladie, HttpStatus.CREATED);
    }

    @Operation(summary = "Recuperer une maladie", description = "cette methode permet de recuperer une maladie à travers son identifiant")
    @GetMapping("/{id}")
    public ResponseEntity<MaladieDTO> getMaladieById(@PathVariable("id") Long id) {
        // Aligné sur ton interface : getMaladieById
        MaladieDTO maladieDTO = maladieServiceInterface.getMaladieById(id);
        return new ResponseEntity<>(maladieDTO, HttpStatus.OK);
    }

    @Operation(summary = "Recuperation maladies", description = "Permet de recuperer toutes les maladies !")
    @GetMapping
    public ResponseEntity<List<MaladieDTO>> getAllMaladies() {
        // Aligné sur ton interface : getAllMaladies
        List<MaladieDTO> maladies = maladieServiceInterface.getAllMaladies();
        return new ResponseEntity<>(maladies, HttpStatus.OK);
    }

    @Operation(summary = "Supprimer une maladie", description = "Permet de supprimer une maladie à travers son identifiant !")
    // modification

    @PutMapping("/{id}")
    public ResponseEntity<MaladieDTO> updateMaladie(@PathVariable("id") Long id, @RequestBody MaladieDTO maladieDTO) {
        // Appelle la méthode updateMaladie de ton service
        MaladieDTO updatedMaladie = maladieServiceInterface.updateMaladie(id, maladieDTO);
        return new ResponseEntity<>(updatedMaladie, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaladie(@PathVariable("id") Long id) {
        // Aligné sur ton interface : deleteMaladie
        maladieServiceInterface.deleteMaladie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}