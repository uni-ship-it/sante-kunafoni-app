package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.UtilisateurDTO;
import groupe3.example.santekunafoniapp.Entity.*;
import groupe3.example.santekunafoniapp.repository.UtilisateurRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.UtilisateurServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImplementation implements UtilisateurServiceInterface {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImplementation(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void addUtilisateur(UtilisateurDTO uDTO) {

        Utilisateur utilisateur;

        if (uDTO.getRole() == Role.ADMIN) {
            utilisateur = new Administrateur();
        } else if (uDTO.getRole() == Role.AGENT_SANTE) {
            utilisateur = new AgentSante();
        } else {
            utilisateur = new Patient();
        }

        utilisateur.setNom(uDTO.getNom());
        utilisateur.setPrenom(uDTO.getPrenom());
        utilisateur.setTel(uDTO.getTel());
        utilisateur.setMotpass(uDTO.getMotPass());
        utilisateur.setRole(uDTO.getRole());

        utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public void updateUtilisateur(Long id, UtilisateurDTO uDTO) {

        Optional<Utilisateur> existingUtilisateur = utilisateurRepository.findById(id);

        if (existingUtilisateur.isPresent()) {
            Utilisateur addingUtilisateur = existingUtilisateur.get();

            addingUtilisateur.setNom(uDTO.getNom());
            addingUtilisateur.setPrenom(uDTO.getPrenom());
            addingUtilisateur.setTel(uDTO.getTel());
            addingUtilisateur.setMotpass(uDTO.getMotPass());
            addingUtilisateur.setRole(uDTO.getRole());

            utilisateurRepository.save(addingUtilisateur);
        }
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}