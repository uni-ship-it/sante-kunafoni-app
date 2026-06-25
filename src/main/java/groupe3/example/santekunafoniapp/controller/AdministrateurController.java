package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.AdministrateurDTO;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    private final AdministrateurServiceInterface administrateurService;

    public AdministrateurController(AdministrateurServiceInterface administrateurService) {
        this.administrateurService = administrateurService;
    }

    @PostMapping
    public AdministrateurDTO ajouter(@RequestBody AdministrateurDTO dto){
        return administrateurService.ajouter(dto);
    }


    @GetMapping
    public List<AdministrateurDTO> afficherTous(){
        return administrateurService.afficherTous();
    }

    @GetMapping("/{id}")
    public AdministrateurDTO afficherParId(@PathVariable Long id){
        return administrateurService.afficherParId(id);
    }

    @PutMapping("/{id}")
    public AdministrateurDTO modifier(
            @PathVariable Long id,
            @RequestBody AdministrateurDTO dto){

        return administrateurService.modifier(id,dto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id){

        administrateurService.supprimer(id);
    }

}