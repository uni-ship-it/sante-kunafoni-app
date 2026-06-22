package groupe3.example.santekunafoniapp.services.serviceImplementation;



import groupe3.example.santekunafoniapp.Entity.AgentSante;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentSanteService {

    private final AgentSanteRepository repository;

    public AgentSanteService(AgentSanteRepository repository) {
        this.repository = repository;
    }

    public AgentSante ajouterAgent(AgentSante agent) {
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

        ancien.setSpecialite(agent.getSpecialite());
        ancien.setCentre(agent.getCentre());
        ancien.setEmail(agent.getEmail());

        return repository.save(ancien);
    }

    public void supprimerAgent(Long id) {
        repository.deleteById(id);
    }
}