package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.AdministrateurRequestDTO;
import groupe3.example.santekunafoniapp.DTO.AdministrateurResponseDTO;
import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdministrateurServiceImplementation
        implements AdministrateurServiceInterface {

    private final AdministrateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdministrateurServiceImplementation(
            AdministrateurRepository repository, PasswordEncoder passwordEncoder){

        this.repository = repository;
        //awa
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdministrateurResponseDTO ajouter(AdministrateurRequestDTO dto){

        Administrateur admin = new Administrateur();

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setEmail(dto.getEmail());
        admin.setTel(dto.getTel());

        // Hachage du mot de passe et attribution automatique du rôle Admin by awa
        if (dto.getMotpass() != null && !dto.getMotpass().isEmpty()){
            admin.setMotpass(passwordEncoder.encode(dto.getMotpass()));
        }

        admin.setRole(Role.ADMIN);

        Administrateur savedAdmin = repository.save(admin);

        return convertirEnResponseDTO(savedAdmin);    }

    @Override
    public AdministrateurResponseDTO afficherParId(Long id){

        Administrateur admin =
                repository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Administrateur introuvable"
                        ));
        return convertirEnResponseDTO(admin);
    }

    @Override
    public List<AdministrateurResponseDTO> afficherTous(){
        return repository.findAll()
                .stream()
                .map(this::convertirEnResponseDTO)
                .toList();
    }

    @Override
    public String modifier(Long id, AdministrateurRequestDTO dto){

        Administrateur admin =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Administrateur introuvable"
                                )
                        );

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setEmail(dto.getEmail());
        admin.setTel(dto.getTel());

        if (dto.getMotpass() != null && !dto.getMotpass().isEmpty()){
            admin.setMotpass(passwordEncoder.encode(dto.getMotpass()));
        }

        repository.save(admin);
        return "Modification réussie";
    }

    @Override
    public String supprimer(Long id){

        Administrateur admin = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Administrateur introuvable"));

        repository.delete(admin);
        return "La suppression a été effectuée avec succès";
    }

    private AdministrateurResponseDTO convertirEnResponseDTO(Administrateur admin){
        AdministrateurResponseDTO dto = new AdministrateurResponseDTO();
        dto.setIdUtilisateur(admin.getIdUtilisateur());
        dto.setNom(admin.getNom());
        dto.setPrenom(admin.getPrenom());
        dto.setEmail(admin.getEmail());
        dto.setTel(admin.getTel());
        if (admin.getRole() != null) {
            dto.setRole(admin.getRole().name());
        } else {
            dto.setRole("NON_DEFINI");
        }

        return dto;
    }
}