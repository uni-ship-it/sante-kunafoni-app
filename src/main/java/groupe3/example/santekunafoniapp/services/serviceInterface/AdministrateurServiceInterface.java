package groupe3.example.santekunafoniapp.services.serviceInterface;


import groupe3.example.santekunafoniapp.DTO.AdministrateurDTO;

import java.util.List;


public interface AdministrateurServiceInterface {


    AdministrateurDTO ajouter(AdministrateurDTO dto);


    AdministrateurDTO modifier(Long id, AdministrateurDTO dto);


    AdministrateurDTO afficherParId(Long id);


    List<AdministrateurDTO> afficherTous();


    void supprimer(Long id);

}
