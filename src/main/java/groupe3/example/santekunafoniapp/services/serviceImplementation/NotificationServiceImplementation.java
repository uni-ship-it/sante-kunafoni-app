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

    // ── NOTIFICATION ORDINAIRE ────────────────────────────────
    // Envoyée manuellement par un agent ou admin
    // datePublication est automatiquement définie à maintenant
    @Override
    public Notification envoyerNotification(Notification notif) {
        notif.setDatePublication(LocalDateTime.now());
        return repository.save(notif);
    }

    // ── RÉCUPÉRATION ─────────────────────────────────────────
    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUtilisateur(Long userId) {
        return repository.findByUtilisateur_IdUtilisateur(userId);
    }

    // ── MARQUER COMME LUE ────────────────────────────────────
    @Override
    public void marquerCommeLue(Long id) {
        Notification notif = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        notif.setLue(true);
        repository.save(notif);
    }

    // ── ALERTE AUTOMATIQUE ÉPIDÉMIE ──────────────────────────
    // Condition : nombre de Traitement liés à cette Maladie
    // avec datedebut >= aujourd'hui - 7 jours > 30
    // Si condition remplie → crée automatiquement une Notification
    // Si condition non remplie → ne fait rien
    @Override
    public void verifierEpidemie(Long idMaladie) {

        // 1. Vérifie que la maladie existe
        Maladie maladie = maladieRepository.findById(idMaladie)
                .orElseThrow(() -> new RuntimeException("Maladie non trouvée"));

        // 2. Fenêtre glissante : les 7 derniers jours
        LocalDate dateDebutPeriode = LocalDate.now().minusDays(PERIODE_JOURS);

        // 3. Compte les cas enregistrés via les Traitement
        long nombreCas = traitementRepository.countCasParMaladieDepuis(
                idMaladie,
                dateDebutPeriode
        );

        // 4. CONDITION PRINCIPALE : seuil dépassé ?
        if (nombreCas > SEUIL_EPIDEMIE) {

            // 5. Crée automatiquement la notification d'alerte
            Notification alerte = new Notification();
            alerte.setTitre("⚠️ Alerte épidémie possible");
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
            // Pas d'utilisateur assigné → alerte globale système
            alerte.setUtilisateur(null);

            repository.save(alerte);
        }
        // Si nombreCas <= 30 → rien, pas de notification créée
    }
}