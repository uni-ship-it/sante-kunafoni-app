package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.DTO.MaladieDTO;
import java.util.List;

public interface MaladieServiceInterface {

        // Lister toutes les maladies
        List<MaladieDTO> getAllMaladies();

        // Récupérer une maladie par son ID (Ajouté pour le contrôleur)
        MaladieDTO getMaladieById(Long idMaladie);

        // Ajouter une maladie
        MaladieDTO createMaladie(MaladieDTO maladieDTO);

        // Modifier une maladie
        MaladieDTO updateMaladie(Long idMaladie, MaladieDTO maladieDTO);

        // Modifier une maladie partiellement
        MaladieDTO partialUpdateMaladie(Long idMaladie, MaladieDTO maladieDTO);

        // Supprimer une maladie
        void deleteMaladie(Long idMaladie);
}