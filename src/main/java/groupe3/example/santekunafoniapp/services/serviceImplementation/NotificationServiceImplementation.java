package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekounafoniapp.model.Notification;
import groupe3.example.santekounafoniapp.repository.NotificationRepository;
import groupe3.example.santekounafoniapp.services.serviceInterface.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Override
    public Notification envoyerNotification(Notification notif) {
        notif.setDatePublication(LocalDateTime.now());
        notif.setLue(false);
        return repository.save(notif);
    }

    @Override
    public void marquerCommeLue(Long id) {
        Notification notif = repository.findById(id).orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        notif.setLue(true);
        repository.save(notif);
    }

    @Override
    public List<Notification> getNotificationsByUtilisateur(Long userId) {
        return repository.findByUtilisateurId(userId);
    }
}