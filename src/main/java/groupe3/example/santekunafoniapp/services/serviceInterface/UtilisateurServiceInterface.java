package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurServiceInterface {
    List<Utilisateur> getAllUtilisateur();
    void addUtilisateur(UtilisateurDTO uDTO);
    Optional<Utilisateur> getUtilisateurById(Long id);
    void updateUtilisateur(Long id, UtilisateurDTO uDTO);
    void deleteUtilisateur(Long id);
}
