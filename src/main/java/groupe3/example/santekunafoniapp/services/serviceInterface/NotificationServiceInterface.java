package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.Notification;
import java.util.List;

public interface NotificationServiceInterface {

    // Notification ordinaire — envoyée manuellement
    Notification envoyerNotification(Notification notif);

    // Récupération
    List<Notification> getAllNotifications();
    List<Notification> getNotificationsByUtilisateur(Long userId);

    // Marquer comme lue
    void marquerCommeLue(Long id);

    // Alerte automatique — déclenché quand seuil 30 cas / 7 jours dépassé
    void verifierEpidemie(Long idMaladie);
}