package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Symptome", description = "Gestion des symptome")
@RestController
@RequestMapping("/api/symptomes")
public class SymptomeController {

    private final SymptomeServiceInterface service;

    public SymptomeController(SymptomeServiceInterface service) {
        this.service = service;
    }

    @Operation(summary = "Recuperer les symptomes", description = "Cette methode permet de recuperer tous symtomes !")
    @GetMapping
    public List<Symptome> getAll(){
        return service.getAllSymptome();
    }

    @Operation(summary = "Recuperer les symptomes", description = "Cette methode permet de recuperer un symtome à travers son identifiant !")
    @GetMapping("/{id}")
    public Symptome getById(@PathVariable Long id){
        return service.getSymptomeById(id);
    }

    @Operation(summary = "Recuperer un symptome", description = "Cette methode permet de recuperer un symtome à travers son identifiant !")
    @PostMapping
    public Symptome createSymptome(@RequestBody Symptome symptome){
        return service.createSymptome(symptome);
    }

    @Operation(summary = "Créer un symptome", description = "Cette methode permet de créer un symtome !")
    @PutMapping("/{id}")
    public Symptome updateById(@PathVariable  Long id, @RequestBody Symptome symptome) {
        return service.updateSymptomeById(id, symptome);
    }

    @Operation(summary = "Supprimer un symptome", description = "Cette methode permet de supprimer un symtome !")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
//        return service.deleteSymptomeById(id);
    }
}


