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

    // ============================================================
    // 1. NOTIFICATION ORDINAIRE
    // ============================================================
    @Override
    public Notification envoyerNotification(Notification notif) {
        notif.setDatePublication(LocalDateTime.now());
        notif.setLue(false);
        return repository.save(notif);
    }

    // ============================================================
    // 2. RÉCUPÉRATION
    // ============================================================
    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAllByOrderByDatePublicationDesc();
    }

    @Override
    public List<Notification> getNotificationsByUtilisateur(Long userId) {
        return repository.findByUtilisateur_IdUtilisateurOrderByDatePublicationDesc(userId);
    }

    @Override
    public List<Notification> getNotificationsSysteme() {
        return repository.findByUtilisateurIsNullOrderByDatePublicationDesc();
    }

    // ============================================================
    // 3. MARQUER COMME LUE
    // ============================================================
    @Override
    public void marquerCommeLue(Long id) {
        Notification notif = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée avec l'ID : " + id));
        notif.setLue(true);
        repository.save(notif);
    }

    // ============================================================
    // 4. ALERTE AUTOMATIQUE ÉPIDÉMIE
    // ============================================================
    @Override
    public void verifierEpidemie(Long idMaladie) {

        Maladie maladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie non trouvée avec l'ID : " + idMaladie));

        LocalDate dateDebutPeriode = LocalDate.now().minusDays(PERIODE_JOURS);

        long nombreCas = traitementRepository.countCasParMaladieDepuis(
                idMaladie,
                dateDebutPeriode
        );

        if (nombreCas > SEUIL_EPIDEMIE) {

            // ✅ VÉRIFIER SI UNE ALERTE EXISTE DÉJÀ (dans les dernières 24h)
            boolean alerteExistante = repository
                    .findByTitreAndDatePublicationAfter(
                            "Alerte épidémie possible",
                            LocalDateTime.now().minusHours(24)
                    )
                    .stream()
                    .anyMatch(n -> n.getMessage().contains(maladie.getNom()));

            if (alerteExistante) {
                System.out.println("Alerte déjà envoyée pour : " + maladie.getNom() + " (ignoré)");
                return;  // ✅ Ne pas créer de doublon
            }

            // Créer une NOUVELLE alerte (UNIQUEMENT si pas de doublon)
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
            alerte.setUtilisateur(null);

            repository.save(alerte);
            System.out.println("✅ [NOUVELLE ALERTE] Alerte épidémie créée pour : " + maladie.getNom());
        } else {
            System.out.println("Pas d'alerte pour : " + maladie.getNom() +
                    " (" + nombreCas + " cas < " + SEUIL_EPIDEMIE + ")");
        }
    }

    // SUPPRIMER UNE NOTIFICATION

    @Override
    public void supprimerNotification(Long id) {
        Notification notif = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée avec l'ID : " + id));
        repository.delete(notif);
        System.out.println("Notification supprimée : ID " + id);
    }
}