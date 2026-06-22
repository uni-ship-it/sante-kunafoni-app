package groupe3.example.santekunafoniapp.services;

import groupe3.example.santekunafoniapp.Entity.Traitement;
import groupe3.example.santekunafoniapp.Repository.TraitementRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraitementService {

    private final TraitementRepository repository;

    public TraitementService(TraitementRepository repository) {

        this.repository = repository;

    }

    // CREATE
    public Traitement ajouterTraitement(Traitement traitement) {

        return repository.save(traitement);

    }

    // READ ALL
    public List<Traitement> getAllTraitements() {

        return repository.findAll();

    }

    // READ BY ID
    public Optional<Traitement> getTraitementById(Long id) {

        return repository.findById(id);

    }

    // UPDATE
    public Traitement modifierTraitement(Long id, Traitement traitement) {


        Traitement ancien = repository.findById(id)

                .orElseThrow(() ->
                        new RuntimeException("Traitement non trouvé"));

        ancien.setNomTraitement(traitement.getNomTraitement());

        ancien.setDatedebut(traitement.getDatedebut());

        ancien.setDatefin(traitement.getDatefin());

        ancien.setDescription(traitement.getDescription());

        return repository.save(ancien);

    }

    // DELETE
    public void supprimerTraitement(Long id) {


        repository.deleteById(id);

    }

}