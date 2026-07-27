package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // RECHERCHER LES ALERTES PAR TITRE ET DATE
    List<Notification> findByTitreAndDatePublicationAfter(
            String titre,
            LocalDateTime date
    );

    // TOUTES LES NOTIFICATIONS TRIÉES PAR DATE
    List<Notification> findAllByOrderByDatePublicationDesc();

    // NOTIFICATIONS D'UN UTILISATEUR TRIÉES
    List<Notification> findByUtilisateur_IdUtilisateurOrderByDatePublicationDesc(Long userId);

    // NOTIFICATIONS SYSTÈME
    List<Notification> findByUtilisateurIsNullOrderByDatePublicationDesc();
}