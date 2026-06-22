package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;
import java.util.List;

public interface SymptomeMaladieServiceInterface {
    SymptomeMaladieDTO ajouter(SymptomeMaladieDTO dto);
    List<SymptomeMaladieDTO> getAll();
    List<SymptomeMaladieDTO> getByMaladie(Integer idMaladie);
    List<SymptomeMaladieDTO> getBySymptome(Integer idSymptome);
    void supprimer(Integer idSymptome, Integer idMaladie);
}