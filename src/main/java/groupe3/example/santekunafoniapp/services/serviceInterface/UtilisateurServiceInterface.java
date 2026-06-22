package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurServiceInterface {
    List<Utilisateur> getAllUtilisateur();
    Utilisateur addUtilisateur(Utilisateur utilisateur);
    Optional<Utilisateur> getUtilisateurById(Long id);
    void updateUtilisateur(Long id, Utilisateur utilisateur);
    void deleteUtilisateur(Long id);
}
