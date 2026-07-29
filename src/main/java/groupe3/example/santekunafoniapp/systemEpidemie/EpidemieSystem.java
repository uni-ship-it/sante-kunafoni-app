package groupe3.example.santekunafoniapp.systemEpidemie;

import groupe3.example.santekunafoniapp.Repository.MaladieRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.NotificationServiceInterface;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EpidemieSystem {

    private final MaladieRepository maladieRepository;
    private final NotificationServiceInterface notificationService;

    public EpidemieSystem(MaladieRepository maladieRepository,
                          NotificationServiceInterface notificationService) {
        this.maladieRepository = maladieRepository;
        this.notificationService = notificationService;
    }

    /**
     * Vérification automatique des risques épidémiques
     * TOUTES LES 30 SECONDES (pour test)
     * À changer en 21600000 (6h) en production
     */
    @Scheduled(fixedRate = 30000)  // 30 secondes en millisecondes
    public void verifierToutesLesMaladies() {
        System.out.println("[SYSTEME EPIDEMIE] Vérification en cours à " + new java.util.Date());

        maladieRepository.findAll().forEach(maladie -> {
            //Utilisation du bon getter : getIdMaladie()
            notificationService.verifierEpidemie(maladie.getIdMaladie());
        });

        System.out.println("[SYSTEME EPIDEMIE] Vérification terminée à " + new java.util.Date());
    }
}