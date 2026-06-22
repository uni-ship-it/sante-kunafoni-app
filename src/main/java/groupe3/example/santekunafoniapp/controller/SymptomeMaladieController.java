package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;
import groupe3.example.santekunafoniapp.services.serviceImplementation.SymptomeMaladieServiceImpl;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeMaladieServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptome-maladie")
@CrossOrigin(origins = "*")
public class SymptomeMaladieController {

    private final SymptomeMaladieServiceImpl service;

    public SymptomeMaladieController(SymptomeMaladieServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public SymptomeMaladieDTO ajouter(@RequestBody SymptomeMaladieDTO dto) {
        return service.ajouter(dto);
    }

    @GetMapping
    public List<SymptomeMaladieDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/maladie/{idMaladie}")
    public List<SymptomeMaladieDTO> getByMaladie(@PathVariable Integer idMaladie) {
        return service.getByMaladie(idMaladie);
    }

    @GetMapping("/symptome/{idSymptome}")
    public List<SymptomeMaladieDTO> getBySymptome(@PathVariable Integer idSymptome) {
        return service.getBySymptome(idSymptome);
    }

    @DeleteMapping("/{idSymptome}/{idMaladie}")
    public void supprimer(@PathVariable Long idSymptome, @PathVariable Integer idMaladie) {
        service.supprimer(idMaladie, idMaladie);
    }
}