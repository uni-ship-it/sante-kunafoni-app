package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;
import org.springframework.stereotype.Service;
import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.Repository.SymptomeRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;

import java.util.List;

@Service
public class SymptomeServiceImplementation implements SymptomeServiceInterface {

    private final SymptomeRepository repository;

    public SymptomeServiceImplementation(SymptomeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Symptome> getAllSymptome() {
        return repository.findAll();
    }

    @Override
    public Symptome getSymptomeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Symptôme introuvable"));
    }

    @Override
    public Symptome createSymptome(Symptome symptome) {
        return repository.save(symptome);
    }

    @Override
    public Symptome updateSymptomeById(Long id, Symptome symptome) {
        Symptome ancien=repository.findById(id)
        .orElseThrow(()-> new RuntimeException("Symptôme introuvable"));
        ancien.setNom(symptome.getNom());
        ancien.setDescription(symptome.getDescription());
        return repository.save(ancien);
    }

    @Override
    public void deleteSymptomeById(Long id) {
        repository.deleteById(id);
    }
}