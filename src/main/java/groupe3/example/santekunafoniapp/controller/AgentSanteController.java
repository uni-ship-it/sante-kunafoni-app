package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.AgentSante;
import groupe3.example.santekunafoniapp.services.serviceImplementation.AgentSanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Agents de santé", description = "Gestion des agents de santé")
@RestController
@RequestMapping("/api/agents")
public class AgentSanteController {

    private final AgentSanteService service;

    public AgentSanteController(AgentSanteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Créer un agent de santé",
            description = "Enregistre un nouvel agent de santé dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agent créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public AgentSante ajouterAgent(@RequestBody AgentSante agent) {
        return service.ajouterAgent(agent);
    }

    @Operation(
            summary = "Lister tous les agents de santé",
            description = "Retourne la liste complète de tous les agents de santé."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<AgentSante> getAllAgents() {
        return service.getAllAgents();
    }

    @Operation(
            summary = "Récupérer un agent par ID",
            description = "Retourne les détails d'un agent de santé à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agent trouvé"),
            @ApiResponse(responseCode = "404", description = "Agent non trouvé")
    })
    @GetMapping("/{id}")
    public AgentSante getAgentById(
            @Parameter(description = "ID de l'agent de santé", required = true)
            @PathVariable Long id
    ) {
        return service.getAgentById(id)
                .orElseThrow(() -> new RuntimeException("Agent non trouvé"));
    }

    @Operation(
            summary = "Modifier un agent de santé",
            description = "Met à jour les informations d'un agent de santé existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agent modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Agent non trouvé")
    })
    @PutMapping("/{id}")
    public AgentSante modifierAgent(
            @Parameter(description = "ID de l'agent à modifier", required = true)
            @PathVariable Long id,
            @RequestBody AgentSante agent
    ) {
        return service.modifierAgent(id, agent);
    }

    @Operation(
            summary = "Supprimer un agent de santé",
            description = "Supprime définitivement un agent de santé à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agent supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Agent non trouvé")
    })
    @DeleteMapping("/{id}")
    public String supprimerAgent(
            @Parameter(description = "ID de l'agent à supprimer", required = true)
            @PathVariable Long id
    ) {
        service.supprimerAgent(id);
        return "Agent supprimé avec succès";
    }
}