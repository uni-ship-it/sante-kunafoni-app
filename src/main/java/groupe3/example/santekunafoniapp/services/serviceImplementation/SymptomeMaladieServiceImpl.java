package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;
import groupe3.example.santekunafoniapp.Entity.SymptomeMaladie;
import groupe3.example.santekunafoniapp.Repository.SymptomeMaladieRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeMaladieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymptomeMaladieServiceImpl implements SymptomeMaladieService {

    private final SymptomeMaladieRepository sympMaladieRepository;

    // Injection par constructeur
    public SymptomeMaladieServiceImpl(SymptomeMaladieRepository sympMaladieRepository) {
        this.sympMaladieRepository = sympMaladieRepository;
    }

    @Override
    @Transactional
    public SymptomeMaladieDTO ajouter(SymptomeMaladieDTO dto) {
        // TODO
        return new SymptomeMaladieDTO();
    }

    @Override
    public List<SymptomeMaladieDTO> getAll() {
        return null;
    }

    @Override
    public List<SymptomeMaladieDTO> getByMaladie(Integer idMaladie) {

        return null;
    }

    @Override
    public List<SymptomeMaladieDTO> getBySymptome(Long idSymptome) {
        return null;
    }

    @Override
    @Transactional
    public void supprimer(Long idSymptome, Integer idMaladie) {
        //
    }
}
