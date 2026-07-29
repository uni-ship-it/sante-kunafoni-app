package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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



    // Compte le nombre de notifications publiées par mois pour l'année en cours
    @Query("SELECT MONTH(n.datePublication) AS mois, COUNT(n) AS total " +
            "FROM Notification n " +
            "WHERE YEAR(n.datePublication) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(n.datePublication) " +
            "ORDER BY MONTH(n.datePublication)")
    List<Object[]> compterNotificationsParMois();



}
