package groupe3.example.santekunafoniapp.services;

import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public class NotificationService {
    @Service
    public class NotificationService {
        @Autowired
        private NotificationRepository repository;

        public Notification envoyerNotification(Notification notif) {
            notif.setDatePublication(LocalDateTime.now());
            notif.setLue(false);
            return repository.save(notif);
        }

        public void marquerCommeLue(Long id) {
            Notification notif = repository.findById(id).orElseThrow();
            notif.setLue(true);
            repository.save(notif);
        }
    }
}
