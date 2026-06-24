package groupe3.example.santekunafoniapp.controller;

import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationServiceInterface service;

    public NotificationController(NotificationServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/utilisateur/{userId}")
    public List<Notification> getByUtilisateur(@PathVariable Long userId) {
        return service.getNotificationsByUtilisateur(userId);
    }

    @PostMapping
    public Notification envoyerNotification(@RequestBody Notification notif) {
        return service.envoyerNotification(notif);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }
}