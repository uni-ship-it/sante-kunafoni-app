package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.TraitementDTO;
import groupe3.example.santekunafoniapp.Entity.Traitement;
import groupe3.example.santekunafoniapp.Repository.TraitementRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.TraitementServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TraitementServiceImplementation implements TraitementServiceInterface {

    private final TraitementRepository repository;
    public TraitementServiceImplementation(TraitementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Traitement ajouterTraitement(TraitementDTO dto) {
        Traitement traitement = new Traitement();
        return mapperEtSauvegarder(traitement, dto);
    }

    @Override
    public List<Traitement> getAllTraitements() {
        return repository.findAll();
    }

    @Override
    public Optional<Traitement> getTraitementById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Traitement modifierTraitement(Long id, TraitementDTO dto) {
        Traitement ancien = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traitement non trouvé"));
        return mapperEtSauvegarder(ancien, dto);
    }

    @Override
    public void supprimerTraitement(Long id) {
        repository.deleteById(id);
    }

    // Méthode partagée pour convertir le DTO vers l'entité
    private Traitement mapperEtSauvegarder(Traitement traitement, TraitementDTO dto) {
        traitement.setNomTraitement(dto.getNomTraitement());
        traitement.setDatedebut(dto.getDatedebut());
        traitement.setDatefin(dto.getDatefin());
        traitement.setDescription(dto.getDescription());

        return repository.save(traitement);
    }
}