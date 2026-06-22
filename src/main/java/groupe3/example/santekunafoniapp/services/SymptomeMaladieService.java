package groupe3.example.santekunafoniapp.services;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;

import java.util.List;

public interface SymptomeMaladieService {
    SymptomeMaladieDTO ajouter(SymptomeMaladieDTO dto);
    List<SymptomeMaladieDTO> getAll();
    List<SymptomeMaladieDTO> getByMaladie(Integer idMaladie);
    List<SymptomeMaladieDTO> getBySymptome(Long idSymptome);
    void supprimer(Long idSymptome, Integer idMaladie);
}
