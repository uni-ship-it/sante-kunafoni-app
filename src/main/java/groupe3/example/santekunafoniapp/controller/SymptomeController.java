package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptomes")
public class SymptomeController {

    private final SymptomeServiceInterface service;

    public SymptomeController(SymptomeServiceInterface service) {
        this.service = service;
    }
    @GetMapping
    public List<Symptome> getAll(){
        return service.getAllSymptomes();
    }

    @GetMapping("/{id}")
    public Symptome getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Symptome createSymptome(@RequestBody Symptome symptome){
        return service.createSymptome(symptome);

    }

    @PutMapping("/{id}")
    public Symptome updateById(@PathVariable  Long id, @RequestBody Symptome symptome) {
        return service.updateById(id, symptome);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        return service.deleteById (id);
    }
}


