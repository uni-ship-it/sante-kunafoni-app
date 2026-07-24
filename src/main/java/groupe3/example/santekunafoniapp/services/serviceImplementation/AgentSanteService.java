package groupe3.example.santekunafoniapp.services.serviceImplementation;



import groupe3.example.santekunafoniapp.Entity.AgentSante;
import groupe3.example.santekunafoniapp.Entity.Role;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentSanteService {

    private final AgentSanteRepository repository;
    private final PasswordEncoder passwordEncoder; //declaration du passwordEncoder Awa

    //3. L'injecter dans le constructeur
    public AgentSanteService(AgentSanteRepository repository , PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AgentSante ajouterAgent(AgentSante agent) {
        //Forcer à prendre le role comme AGENT_SANTE
        agent.setRole(Role.AGENT_SANTE);
        //Hasher le mot de passe avant sauvegarder !
        if (agent.getMotpass() != null && !agent.getMotpass().isEmpty()) {
            agent.setMotpass(passwordEncoder.encode(agent.getMotpass()));
        }
        return repository.save(agent);
    }

    public List<AgentSante> getAllAgents() {

        return repository.findAll();
    }

    public Optional<AgentSante> getAgentById(Long id) {
        return repository.findById(id);
    }

    public AgentSante modifierAgent(Long id, AgentSante agent) {

        AgentSante ancien =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Agent non trouvé"));

        ancien.setNom(agent.getNom());
        ancien.setPrenom(agent.getPrenom());

        //On verifie si un nouveau mot de passe a été fourni pour modifier et le hasher aussi
        if (agent.getMotpass() != null && !agent.getMotpass().isEmpty()){
            ancien.setMotpass(agent.getMotpass());
        }

        ancien.setTel(agent.getTel());
        ancien.setRole(agent.getRole());
        ancien.setSpecialite(agent.getSpecialite());
        ancien.setCentre(agent.getCentre());
        ancien.setEmail(agent.getEmail());

        return repository.save(ancien);
    }

    public void supprimerAgent(Long id) {
        repository.deleteById(id);
    }
}