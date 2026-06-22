package groupe3.example.santekunafoniapp.controller;
import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.services.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    @RestController
    @RequestMapping("/api/notifications")
    public class NotificationController {

        @Autowired
        private NotificationServiceInterface service;

        @PostMapping
        public Notification creerNotification(@RequestBody Notification notif) {
            return service.envoyerNotification(notif);
        }

        @GetMapping("/utilisateur/{userId}")
        public List<Notification> getByUtilisateur(@PathVariable Long userId) {
            return service.findByUtilisateurId(userId);
        }

        @PatchMapping("/{id}/read")
        public void marquerLue(@PathVariable Long id) {
            service.marquerCommeLue(id);
        }
    }

