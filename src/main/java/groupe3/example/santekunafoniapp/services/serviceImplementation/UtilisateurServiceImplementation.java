package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Utilisateur;
import groupe3.example.santekunafoniapp.repository.UtilisateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.UtilisateurServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImplementation implements UtilisateurServiceInterface {
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImplementation(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public void updateUtilisateur(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> existingUtilisateur = utilisateurRepository.findById(id);

        if (existingUtilisateur.isPresent()){
            Utilisateur addingUtilisateur = existingUtilisateur.get();

            addingUtilisateur.setNom(utilisateur.getNom());
            addingUtilisateur.setPrenom(utilisateur.getPrenom());
            addingUtilisateur.setTel(utilisateur.getTel());
            addingUtilisateur.setMotpass(utilisateur.getMotpass());
            addingUtilisateur.setRole(utilisateur.getRole());

            utilisateurRepository.save(addingUtilisateur);
        }
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
