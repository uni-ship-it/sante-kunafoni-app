package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;

import java.util.List;

public interface AdministrateurServiceInterface {
    UtilisateurDTO ajouterUtilisateur(UtilisateurDTO dto);
    UtilisateurDTO modifierUtilisateur(Long id,UtilisateurDTO dto);
    void supprimerUtilisateur(Long id);
    List<UtilisateurDTO>afficherUtilisateurs();
    UtilisateurDTO afficherUtilisateurParID(Long id);

    UtilisateurDTO afficherUtilisateurParId(Long id);
}
