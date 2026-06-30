package groupe3.example.santekunafoniapp.services.serviceInterface;


import groupe3.example.santekunafoniapp.DTO.TraitementDTO;
import groupe3.example.santekunafoniapp.Entity.Traitement;


import java.util.List;
import java.util.Optional;

public interface TraitementServiceInterface {

    Traitement ajouterTraitement(TraitementDTO dto);

    List<Traitement> getAllTraitements();

    Optional<Traitement> getTraitementById(Long id);

    Traitement modifierTraitement(Long id, TraitementDTO dto);

    void supprimerTraitement(Long id);


}