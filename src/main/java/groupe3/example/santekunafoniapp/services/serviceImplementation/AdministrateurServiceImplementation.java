package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Repository.AdministrateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdministrateurServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public abstract class AdministrateurServiceImplementation implements AdministrateurServiceInterface {
    public final AdministrateurRepository administrateurRepository;
    public AdministrateurServiceImplementation(AdministrateurRepository administrateurRepository){
        this.administrateurRepository=administrateurRepository;
    }
    @Override
    public UtilisateurDTO ajouterUtilisateur(UtilisateurDTO dto){
        return dto;
    }
    @Override
    public UtilisateurDTO modifierUtilisateur( Long id ,UtilisateurDTO dto) {
        return dto;
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        // delete repository
    }

    @Override
    public List<UtilisateurDTO> afficherUtilisateurs() {
        return new ArrayList<>();
    }
    @Override
    public UtilisateurDTO afficherUtilisateurParId(Long id){
        Administrateur administrateur= administrateurRepository.findById(id).orElseThrow(()->new RuntimeException("Utilisateur introuvable"));
        UtilisateurDTO dto=new UtilisateurDTO();
        dto.setNom(administrateur.getNom());
        dto.setPrenom(administrateur.getPrenom());
        return dto;
    }

}
