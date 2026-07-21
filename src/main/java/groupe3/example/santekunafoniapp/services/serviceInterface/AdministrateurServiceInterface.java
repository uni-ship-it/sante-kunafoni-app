package groupe3.example.santekunafoniapp.services.serviceInterface;


import groupe3.example.santekunafoniapp.DTO.AdministrateurRequestDTO;
import groupe3.example.santekunafoniapp.DTO.AdministrateurResponseDTO;

import java.util.List;


public interface AdministrateurServiceInterface {


    public String ajouter(AdministrateurRequestDTO dto);


    public String modifier(Long id, AdministrateurRequestDTO dto);


    AdministrateurResponseDTO afficherParId(Long id);


    List<AdministrateurResponseDTO> afficherTous();


    public String supprimer(Long id);

}
