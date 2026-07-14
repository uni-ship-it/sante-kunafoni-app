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
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationServiceInterface service;

    public NotificationController(NotificationServiceInterface service) {
        this.service = service;
    }

    // ── GET TOUTES ────────────────────────────────────────────
    @Operation(
            summary = "Lister toutes les notifications",
            description = "Retourne toutes les notifications du système."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }

    // ── GET PAR UTILISATEUR ───────────────────────────────────
    @Operation(
            summary = "Lister les notifications d'un utilisateur",
            description = "Retourne toutes les notifications d'un utilisateur donné."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    @GetMapping("/utilisateur/{userId}")
    public List<Notification> getByUtilisateur(
            @Parameter(description = "ID de l'utilisateur", required = true)
            @PathVariable Long userId
    ) {
        return service.getNotificationsByUtilisateur(userId);
    }

    // ── POST NOTIFICATION ORDINAIRE ───────────────────────────
    @Operation(
            summary = "Envoyer une notification ordinaire",
            description = "Crée et envoie manuellement une notification à un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification envoyée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Notification envoyerNotification(@RequestBody Notification notif) {
        return service.envoyerNotification(notif);
    }

    // ── PUT MARQUER COMME LUE ─────────────────────────────────
    @Operation(
            summary = "Marquer une notification comme lue",
            description = "Met à jour le statut lue d'une notification."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification marquée comme lue"),
            @ApiResponse(responseCode = "404", description = "Notification non trouvée")
    })
    @PutMapping("/{id}/lue")
    public ResponseEntity<String> marquerCommeLue(
            @Parameter(description = "ID de la notification", required = true)
            @PathVariable Long id
    ) {
        try {
            service.marquerCommeLue(id);
            return ResponseEntity.ok("Notification marquée comme lue.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // ── POST VÉRIFICATION ÉPIDÉMIE AUTOMATIQUE ────────────────
    @Operation(
            summary = "Vérifier le risque d'épidémie",
            description = "Analyse les cas enregistrés sur 7 jours. " +
                    "Si plus de 30 cas → alerte automatique créée en base."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vérification effectuée"),
            @ApiResponse(responseCode = "404", description = "Maladie non trouvée")
    })
    @PostMapping("/verifier-epidemie/{idMaladie}")
    public ResponseEntity<String> verifierEpidemie(
            @Parameter(description = "ID de la maladie à surveiller", required = true)
            @PathVariable Long idMaladie
    ) {
        try {
            service.verifierEpidemie(idMaladie);
            return ResponseEntity.ok(
                    "Vérification effectuée pour la maladie ID " + idMaladie + "."
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}