package groupe3.example.santekunafoniapp.services.serviceInterface;


import groupe3.example.santekunafoniapp.DTO.AdministrateurDTO;
import groupe3.example.santekunafoniapp.Entity.AgentSante;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AdministrateurServiceInterface {


    public String ajouter(AdministrateurDTO dto);


    public String modifier(Long id, AdministrateurDTO dto);


    AdministrateurDTO afficherParId(Long id);


    List<AdministrateurDTO> afficherTous();


    public String supprimer(Long id);

}
