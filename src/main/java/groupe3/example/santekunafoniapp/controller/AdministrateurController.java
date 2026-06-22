package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrateurController {
    private final AdministrateurRepository administrateurRepository;
    public AdministrateurController(AdministrateurRepository administrateurRepository){
        this.administrateurRepository=administrateurRepository;
    }
}
