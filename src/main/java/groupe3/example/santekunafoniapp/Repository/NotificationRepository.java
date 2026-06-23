package groupe3.example.santekunafoniapp.Repository;
import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTitreContaining(String titre);
}