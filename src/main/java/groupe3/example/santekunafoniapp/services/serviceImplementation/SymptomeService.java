package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Repository.SymptomeRepository;
import org.springframework.stereotype.Service;

@Service
public class SymptomeService {
    private final SymptomeRepository repository;

    public SymptomeService(SymptomeRepository repository) {
        this.repository = repository;
    }
}
