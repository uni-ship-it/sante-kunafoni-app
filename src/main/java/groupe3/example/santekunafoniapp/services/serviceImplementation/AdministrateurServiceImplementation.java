package groupe3.example.santekunafoniapp.services.serviceImplementation;


import groupe3.example.santekunafoniapp.DTO.AdministrateurDTO;
import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@Service
public class AdministrateurServiceImplementation
        implements AdministrateurServiceInterface {


    private final AdministrateurRepository repository;



    public AdministrateurServiceImplementation(
            AdministrateurRepository repository){

        this.repository = repository;
    }



    @Override
    public  String ajouter(AdministrateurDTO dto){


        Administrateur admin = new Administrateur();

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setEmail(dto.getEmail());
        admin.setTel(dto.getTel());
        admin.setMotpass(dto.getMotpass());
        admin.setRole(Role.valueOf(dto.getRole()));

        repository.save(admin);
        return "Ajouté avec succès";


    }



    @Override
    public AdministrateurDTO afficherParId(Long id){


        Administrateur admin =
                repository.findById(id)
                        .orElseThrow(() ->new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Administrateur introuvable"
        )
                        );


        return convertirDTO(admin);
    }

    @Override
    public List<AdministrateurDTO> afficherTous(){


         return repository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Override
    public String modifier(Long id, AdministrateurDTO dto){

        Administrateur admin =
                repository.findById(id)
                        .orElseThrow(
                                ()-> new RuntimeException("Administrateur introuvable")
                        );

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setEmail(dto.getEmail());
        admin.setTel(dto.getTel());
        admin.setMotpass(dto.getMotpass());
        admin.setRole(Role.valueOf(dto.getRole()));

        convertirDTO(repository.save(admin));
         return "Mofification reussié";

    }

    @Override
    public String supprimer(Long id){

        repository.deleteById(id);
        return "La Suppression a été affectuer";

    }


    private AdministrateurDTO convertirDTO(Administrateur admin){


        AdministrateurDTO dto = new AdministrateurDTO();

        dto.setNom(admin.getNom());

        dto.setPrenom(admin.getPrenom());

        dto.setEmail(admin.getEmail());
        dto.setTel(admin.getTel());
        dto.setMotpass(admin.getMotpass());

        if (admin.getRole() != null) {
            dto.setRole(admin.getRole().name());
        } else {
            dto.setRole("NON_DEFINI");
        }

        return dto;

    }

}
