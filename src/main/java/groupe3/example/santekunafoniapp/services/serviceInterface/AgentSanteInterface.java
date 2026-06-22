
package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.AgentSante;

import java.util.List;
import java.util.Optional;

public interface AgentSanteInterface {

    AgentSante ajouterAgent(AgentSante agent);

    List<AgentSante> getAllAgents();

    Optional<AgentSante> getAgentById(Long id);

    AgentSante modifierAgent(Long id, AgentSante agent);

    void supprimerAgent(Long id);
}
