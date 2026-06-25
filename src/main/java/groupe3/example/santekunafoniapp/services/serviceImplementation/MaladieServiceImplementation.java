package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.MaladieDTO;
import groupe3.example.santekunafoniapp.Entity.Maladie;
import groupe3.example.santekunafoniapp.Repository.MaladieRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.MaladieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaladieServiceImplementation implements MaladieServiceInterface {

    @Autowired
    private MaladieRepository maladieRepository;

    @Override
    public List<MaladieDTO> getAllMaladies() {
        return maladieRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MaladieDTO getMaladieById(Long idMaladie) {
        // Ajout de l'implémentation pour la recherche par ID
        Maladie maladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie introuvable avec l'id : " + idMaladie));
        return convertToDTO(maladie);
    }

    @Override
    public MaladieDTO createMaladie(MaladieDTO maladieDTO) {
        Maladie maladie = convertToEntity(maladieDTO);

        if (maladie.getDateDeclaration() == null) {
            maladie.setDateDeclaration(LocalDate.now());
        }

        return convertToDTO(maladieRepository.save(maladie));
    }

    @Override
    public MaladieDTO updateMaladie(Long idMaladie, MaladieDTO maladieDTO) {
        // Aligné sur le nom du paramètre de l'interface (idMaladie)
        Maladie existingMaladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie introuvable avec l'id : " + idMaladie));

        existingMaladie.setNom(maladieDTO.getNom());
        existingMaladie.setDescription(maladieDTO.getDescription());
        existingMaladie.setDateDeclaration(maladieDTO.getDateDeclaration());

        return convertToDTO(maladieRepository.save(existingMaladie));
    }

    @Override
    public MaladieDTO partialUpdateMaladie(Long idMaladie, MaladieDTO maladieDTO) {
        Maladie existingMaladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie introuvable avec l'id : " + idMaladie));

        // On modifie UNIQUEMENT si la nouvelle valeur n'est pas nulle
        if (maladieDTO.getNom() != null) {
            existingMaladie.setNom(maladieDTO.getNom());
        }
        if (maladieDTO.getDescription() != null) {
            existingMaladie.setDescription(maladieDTO.getDescription());
        }
        if (maladieDTO.getDateDeclaration() != null) {
            existingMaladie.setDateDeclaration(maladieDTO.getDateDeclaration());
        }

        return convertToDTO(maladieRepository.save(existingMaladie));
    }

    @Override
    public void deleteMaladie(Long idMaladie) {
        // Aligné sur le nom du paramètre de l'interface (idMaladie)
        Maladie maladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie introuvable avec l'id : " + idMaladie));

        maladieRepository.delete(maladie);
    }

    // ===== MAPPERS =====

    private MaladieDTO convertToDTO(Maladie maladie) {
        MaladieDTO dto = new MaladieDTO();
        dto.setIdMaladie(maladie.getIdMaladie());
        dto.setNom(maladie.getNom());
        dto.setDescription(maladie.getDescription());
        dto.setDateDeclaration(maladie.getDateDeclaration());
        return dto;
    }

    private Maladie convertToEntity(MaladieDTO dto) {
        Maladie maladie = new Maladie();
        if (dto.getIdMaladie() != null) {
            maladie.setIdMaladie(dto.getIdMaladie());
        }
        maladie.setNom(dto.getNom());
        maladie.setDescription(dto.getDescription());
        maladie.setDateDeclaration(dto.getDateDeclaration());
        return maladie;
    }
}