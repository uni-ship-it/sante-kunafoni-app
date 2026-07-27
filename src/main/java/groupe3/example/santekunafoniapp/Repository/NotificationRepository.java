package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Toutes les notifications triées par date (plus récente en premier)
    List<Notification> findAllByOrderByDatePublicationDesc();

    // Notifications d'un utilisateur triées par date
    List<Notification> findByUtilisateur_IdUtilisateurOrderByDatePublicationDesc(Long userId);

    // Notifications système (sans utilisateur) triées par date
    List<Notification> findByUtilisateurIsNullOrderByDatePublicationDesc();

    //(Optionnel) Compter les notifications non lues d'un utilisateur
    long countByUtilisateur_IdUtilisateurAndLueFalse(Long userId);

    //Compter les notifications système non lues
    long countByUtilisateurIsNullAndLueFalse();
}