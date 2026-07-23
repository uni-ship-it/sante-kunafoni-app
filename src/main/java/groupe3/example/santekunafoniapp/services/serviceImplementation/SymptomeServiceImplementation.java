package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.SymptomeRequestDTO;
import groupe3.example.santekunafoniapp.DTO.SymptomeResponseDTO;
import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.Repository.SymptomeRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.SymptomeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public  class SymptomeServiceImplementation implements SymptomeServiceInterface {
    private final SymptomeRepository repository;

    public SymptomeServiceImplementation(SymptomeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SymptomeResponseDTO> getAllSymptomes() {

        // 1. Récupérer tous les symptômes dans la base de données
        List<Symptome> listeSymptomes = repository.findAll();

        // 2. Créer une liste vide qui contiendra les DTO de réponse
        List<SymptomeResponseDTO> listeResponseDTO = new ArrayList<>();

        // 3. Parcourir chaque symptôme récupéré
        for (Symptome symptome : listeSymptomes) {

            // 4. Transformer le symptôme en DTO de réponse
            SymptomeResponseDTO responseDTO = toResponseDTO(symptome);

            // 5. Ajouter ce DTO dans la liste de réponses
            listeResponseDTO.add(responseDTO);
        }

        // 6. Retourner la liste des DTO au Controller
        return listeResponseDTO;
    }



    @Override
    public SymptomeResponseDTO getById(Long id) {
        Symptome symptome = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Symptôme introuvable"));
        return toResponseDTO(symptome);
    }



    @Override
    public SymptomeResponseDTO createSymptome(SymptomeRequestDTO requestDTO) {
        Symptome symptome = toEntity(requestDTO);
        Symptome symptomeEnregistre = repository.save(symptome);

        return toResponseDTO(symptomeEnregistre);
    }

    @Override
    public SymptomeResponseDTO updateById(Long id,SymptomeRequestDTO requestDTO) {
        Symptome symptome = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Symptôme introuvable"));

        symptome.setNom(requestDTO.getNom());
        symptome.setDescription(requestDTO.getDescription());

        Symptome symptomeModifie = repository.save(symptome);

        return toResponseDTO(symptomeModifie);
    }

    @Override
    public void deleteById(Long id) {
        Symptome symptome = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Symptôme introuvable"));

        repository.delete(symptome);

    }

    // Reçoit les données du client : RequestDTO → Entity
    private Symptome toEntity(SymptomeRequestDTO requestDTO) {
        Symptome symptome = new Symptome();

        symptome.setNom(requestDTO.getNom());
        symptome.setDescription(requestDTO.getDescription());

        return symptome;
    }

    // Prépare les données à renvoyer : Entity → ResponseDTO
    private SymptomeResponseDTO toResponseDTO(Symptome symptome) {
        SymptomeResponseDTO responseDTO = new SymptomeResponseDTO();

        responseDTO.setId(symptome.getId());
        responseDTO.setNom(symptome.getNom());
        responseDTO.setDescription(symptome.getDescription());

        return responseDTO;
    }

}
