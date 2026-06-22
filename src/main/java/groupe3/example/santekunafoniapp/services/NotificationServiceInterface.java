package groupe3.example.santekunafoniapp.services;

import groupe3.example.santekounafoniapp.model.Notification;
import java.util.List;

public interface NotificationServiceInterface {
    Notification envoyerNotification(Notification notif);
    void marquerCommeLue(Long id);
    List<Notification> getNotificationsByUtilisateur(Long userId);
}