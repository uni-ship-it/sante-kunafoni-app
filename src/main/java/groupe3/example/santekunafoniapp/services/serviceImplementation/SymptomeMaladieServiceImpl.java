package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.SymptomeMaladieDTO;
import groupe3.example.santekunafoniapp.Entity.Maladie;
import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.Entity.SymptomeMaladie;
import groupe3.example.santekunafoniapp.Entity.SymptomeMaladieId;
import groupe3.example.santekunafoniapp.Repository.MaladieRepository;
import groupe3.example.santekunafoniapp.Repository.SymptomeMaladieRepository;
import groupe3.example.santekunafoniapp.Repository.SymptomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymptomeMaladieServiceImpl implements SymptomeMaladieService {

    private final SymptomeMaladieRepository symptomeMaladieRepository;
    private final SymptomeRepository symptomeRepository;
    private final MaladieRepository maladieRepository;

    public SymptomeMaladieServiceImpl(SymptomeMaladieRepository symptomeMaladieRepository,
                                      SymptomeRepository symptomeRepository,
                                      MaladieRepository maladieRepository) {
        this.symptomeMaladieRepository = symptomeMaladieRepository;
        this.symptomeRepository = symptomeRepository;
        this.maladieRepository = maladieRepository;
    }

    @Override
    public SymptomeMaladieDTO ajouter(SymptomeMaladieDTO dto) {
        Symptome symptome = symptomeRepository.findById(dto.getIdSymptome())
                .orElseThrow(() -> new RuntimeException("Symptôme introuvable"));

        Maladie maladie = maladieRepository.findById(dto.getIdMaladie())
                .orElseThrow(() -> new RuntimeException("Maladie introuvable"));

        SymptomeMaladieId id = new SymptomeMaladieId(dto.getIdSymptome(), dto.getIdMaladie());

        if (symptomeMaladieRepository.existsById(id)) {
            throw new RuntimeException("Lien symptôme-maladie déjà existant");
        }

        SymptomeMaladie lien = SymptomeMaladie.builder()
                .id(id)
                .symptome(symptome)
                .maladie(maladie)
                .build();

        SymptomeMaladie saved = symptomeMaladieRepository.save(lien);
        return toDTO(saved);
    }

    @Override
    public List<SymptomeMaladieDTO> getAll() {
        return symptomeMaladieRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SymptomeMaladieDTO> getByMaladie(Integer idMaladie) {
        return symptomeMaladieRepository.findByMaladie_IdMaladie(idMaladie)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SymptomeMaladieDTO> getBySymptome(Integer idSymptome) {
        return symptomeMaladieRepository.findBySymptome_IdSymptome(idSymptome)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void supprimer(Integer idSymptome, Integer idMaladie) {
        SymptomeMaladieId id = new SymptomeMaladieId(idSymptome, idMaladie);
        if (!symptomeMaladieRepository.existsById(id)) {
            throw new RuntimeException("Lien introuvable");
        }
        symptomeMaladieRepository.deleteById(id);
    }

    private SymptomeMaladieDTO toDTO(SymptomeMaladie sm) {
        return SymptomeMaladieDTO.builder()
                .idSymptome(sm.getSymptome().getIdSymptome())
                .idMaladie(sm.getMaladie().getIdMaladie())
                .nomSymptome(sm.getSymptome().getNomsymptome())
                .nomMaladie(sm.getMaladie().getNom())
                .build();
    }
}