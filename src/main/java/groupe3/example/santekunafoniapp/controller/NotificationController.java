package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notifications", description = "Gestion des notifications envoyées aux utilisateurs")
@RestController
@RequestMapping("/api/notification") // Chemin unique et correct
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true") // Autorisation CORS pour Angular
public class NotificationController {

    private final NotificationServiceInterface service;

    public NotificationController(NotificationServiceInterface service) {
        this.service = service;
    }

    // ── GET TOUTES ────────────────────────────────────────────
    @Operation(summary = "Lister toutes les notifications")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }

    // ── GET PAR UTILISATEUR ───────────────────────────────────
    @Operation(summary = "Lister les notifications d'un utilisateur")
    @GetMapping("/utilisateur/{userId}")
    public List<Notification> getByUtilisateur(@PathVariable Long userId) {
        return service.getNotificationsByUtilisateur(userId);
    }

    // ── POST NOTIFICATION ORDINAIRE ───────────────────────────
    @Operation(summary = "Envoyer une notification ordinaire")
    @PostMapping
    public Notification envoyerNotification(@RequestBody Notification notif) {
        return service.envoyerNotification(notif);
    }

    // ── PUT MARQUER COMME LUE ─────────────────────────────────
    @Operation(summary = "Marquer une notification comme lue")
    @PutMapping("/{id}/lue")
    public ResponseEntity<String> marquerCommeLue(@PathVariable Long id) {
        try {
            service.marquerCommeLue(id);
            return ResponseEntity.ok("Notification marquée comme lue.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // ── POST VÉRIFICATION ÉPIDÉMIE AUTOMATIQUE ────────────────
    @Operation(summary = "Vérifier le risque d'épidémie")
    @PostMapping("/verifier-epidemie/{idMaladie}")
    public ResponseEntity<String> verifierEpidemie(@PathVariable Long idMaladie) {
        try {
            service.verifierEpidemie(idMaladie);
            return ResponseEntity.ok("Vérification effectuée pour la maladie ID " + idMaladie + ".");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}