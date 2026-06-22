package groupe3.example.santekunafoniapp.controller;



import groupe3.example.santekunafoniapp.Entity.AgentSante;
import groupe3.example.santekunafoniapp.services.serviceImplementation.AgentSanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentSanteController {

    private final AgentSanteService service;

    public AgentSanteController(AgentSanteService service) {
        this.service = service;
    }

    @PostMapping
    public AgentSante ajouterAgent(
            @RequestBody AgentSante agent) {
        return service.ajouterAgent(agent);
    }

    @GetMapping
    public List<AgentSante> getAllAgents() {
        return service.getAllAgents();
    }

    @GetMapping("/{id}")
    public AgentSante getAgentById(
            @PathVariable Long id) {

        return service.getAgentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Agent non trouvé"));
    }

    @PutMapping("/{id}")
    public AgentSante modifierAgent(
            @PathVariable Long id,
            @RequestBody AgentSante agent) {

        return service.modifierAgent(id, agent);
    }

    @DeleteMapping("/{id}")
    public String supprimerAgent(
            @PathVariable Long id) {

        service.supprimerAgent(id);

        return "Agent supprimé avec succès";
    }
}
