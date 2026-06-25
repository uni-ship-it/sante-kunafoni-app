package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(
            summary = "Lister les notifications d'un utilisateur",
            description = "Retourne toutes les notifications associées à un utilisateur donné."
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

    @Operation(
            summary = "Envoyer une notification",
            description = "Crée et envoie une nouvelle notification à un utilisateur."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification envoyée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Notification envoyerNotification(@RequestBody Notification notif) {
        return service.envoyerNotification(notif);
    }

    @Operation(
            summary = "Lister toutes les notifications",
            description = "Retourne la liste complète de toutes les notifications du système."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }
}