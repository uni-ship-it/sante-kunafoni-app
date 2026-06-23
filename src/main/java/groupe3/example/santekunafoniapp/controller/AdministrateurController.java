package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    private final AdministrateurServiceInterface administrateurServiceInterface;
    public AdministrateurController(AdministrateurServiceInterface administrateurServiceInterface){
        this.administrateurServiceInterface=administrateurServiceInterface;
    }
    @PostMapping
    public UtilisateurDTO ajouter(@RequestBody UtilisateurDTO dto){
        return administrateurServiceInterface.ajouterUtilisateur(dto);
    }
    @GetMapping
    public List<UtilisateurDTO>afficherTous(){
        return administrateurServiceInterface.afficherUtilisateurs();
    }
    @GetMapping("/{id}")
    public UtilisateurDTO afficherParId(@PathVariable Long id){
        return administrateurServiceInterface.afficherUtilisateurParID(id);
    }
    @PutMapping("/{id}")
    public UtilisateurDTO modifier(@PathVariable Long id ,@RequestBody UtilisateurDTO dto){
        return administrateurServiceInterface.modifierUtilisateur(id,dto);
    }
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id){
        administrateurServiceInterface.supprimerUtilisateur(id);
    }
}
