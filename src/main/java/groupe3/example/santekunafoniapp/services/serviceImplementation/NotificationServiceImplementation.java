package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Maladie;
import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.Repository.MaladieRepository;
import groupe3.example.santekunafoniapp.Repository.NotificationRepository;
import groupe3.example.santekunafoniapp.Repository.TraitementRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImplementation implements NotificationServiceInterface {

    private final NotificationRepository repository;
    private final TraitementRepository traitementRepository;
    private final MaladieRepository maladieRepository;

    // Seuil : 30 cas en 7 jours = épidémie possible
    private static final int SEUIL_EPIDEMIE = 30;
    private static final int PERIODE_JOURS  = 7;

    public NotificationServiceImplementation(
            NotificationRepository repository,
            TraitementRepository traitementRepository,
            MaladieRepository maladieRepository) {
        this.repository           = repository;
        this.traitementRepository = traitementRepository;
        this.maladieRepository    = maladieRepository;
    }

    // 1. NOTIFICATION ORDINAIRE

    @Override
    public Notification envoyerNotification(Notification notif) {
        notif.setDatePublication(LocalDateTime.now());
        notif.setLue(false);
        return repository.save(notif);
    }


    // 2. RÉCUPÉRATION


    // 2.1 - Toutes les notifications (triées par date décroissante)
    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAllByOrderByDatePublicationDesc();
    }

    // 2.2 - Notifications d'un utilisateur spécifique
    @Override
    public List<Notification> getNotificationsByUtilisateur(Long userId) {
        return repository.findByUtilisateur_IdUtilisateurOrderByDatePublicationDesc(userId);
    }

    // 2.3 - Notifications système (sans utilisateur)
    @Override
    public List<Notification> getNotificationsSysteme() {
        return repository.findByUtilisateurIsNullOrderByDatePublicationDesc();
    }


    // 3. MARQUER COMME LUE

    @Override
    public void marquerCommeLue(Long id) {
        Notification notif = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée avec l'ID : " + id));
        notif.setLue(true);
        repository.save(notif);
    }


    // 4. ALERTE AUTOMATIQUE ÉPIDÉMIE

    @Override
    public void verifierEpidemie(Long idMaladie) {

        // 1. Vérifier que la maladie existe
        Maladie maladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie non trouvée avec l'ID : " + idMaladie));

        // 2. Fenêtre glissante : les 7 derniers jours
        LocalDate dateDebutPeriode = LocalDate.now().minusDays(PERIODE_JOURS);

        // 3. Compter les cas enregistrés via les Traitement
        long nombreCas = traitementRepository.countCasParMaladieDepuis(
                idMaladie,
                dateDebutPeriode
        );

        // 4. CONDITION PRINCIPALE : seuil dépassé ?
        if (nombreCas > SEUIL_EPIDEMIE) {

            // 5. Créer automatiquement la notification d'alerte
            Notification alerte = new Notification();
            alerte.setTitre("Alerte épidémie possible");
            alerte.setMessage(String.format(
                    "Risque d'épidémie détecté pour la maladie '%s' : " +
                            "%d cas enregistrés ces %d derniers jours. " +
                            "Seuil d'alerte : %d cas.",
                    maladie.getNom(),
                    nombreCas,
                    PERIODE_JOURS,
                    SEUIL_EPIDEMIE
            ));
            alerte.setDatePublication(LocalDateTime.now());
            alerte.setLue(false);
            alerte.setUtilisateur(null);  // Notification système

            repository.save(alerte);

            System.out.println("Alerte épidémie créée pour : " + maladie.getNom());
        } else {
            System.out.println("Pas d'alerte pour : " + maladie.getNom() +
                    " (" + nombreCas + " cas < " + SEUIL_EPIDEMIE + ")");
        }
    }
}