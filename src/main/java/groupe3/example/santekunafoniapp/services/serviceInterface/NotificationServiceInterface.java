package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.Notification;
import java.util.List;

public interface NotificationServiceInterface {
    Notification envoyerNotification(Notification notif);
    List<Notification> getAllNotifications();
}