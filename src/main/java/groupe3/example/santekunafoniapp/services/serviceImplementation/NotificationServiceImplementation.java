package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.Repository.NotificationRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImplementation implements NotificationServiceInterface {

    private final NotificationRepository repository;

    public NotificationServiceImplementation(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification envoyerNotification(Notification notif) {
        notif.setDatePublication(LocalDateTime.now());
        return repository.save(notif);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    @Override
    public void marquerCommeLue(Long id) {
        Notification notif = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        notif.setLue(true);
        repository.save(notif);
    }

    @Override
    public List<Notification> getNotificationsByUtilisateur(Long userId) {
        return repository.findByUtilisateur_IdUtilisateur(userId);
    }
}