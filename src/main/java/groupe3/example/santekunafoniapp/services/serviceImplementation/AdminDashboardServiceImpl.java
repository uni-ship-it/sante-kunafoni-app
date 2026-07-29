package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.DashboardStatsDTO;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import groupe3.example.santekunafoniapp.Repository.NotificationRepository;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdminDashboardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardServiceInterface {
    //Declaration des variables
    private final AgentSanteRepository agentSanteRepository;
    private final PatientRepository patientRepository;
    private final NotificationRepository notificationRepository;

    //implemenation de l'interface
    @Override
    public DashboardStatsDTO getDashboardStatsDTO() {
        long totalAgents = agentSanteRepository.count();
        long totalPatients = patientRepository.count();
        long totalNotifications = notificationRepository.count();

        // 1. Récupération des données réelles enregistrées en BDD
        List<Object[]> resultats = notificationRepository.compterNotificationsParMois();

        List<String> labels = new ArrayList<>();
        List<Long> donnees = new ArrayList<>();

        // Tableau pour convertir les numéros de mois (1, 2, 3...) en texte
        String[] nomsMois = {"Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Août", "Sep", "Oct", "Nov", "Déc"};

        // 2. Traitement des résultats de la BDD
        for (Object[] ligne : resultats) {
            int numeroMois = ((Number) ligne[0]).intValue(); // ex: 1 pour Janvier
            long totalAlerte = ((Number) ligne[1]).longValue(); // ex: 15 notifications

            labels.add(nomsMois[numeroMois - 1]);
            donnees.add(totalAlerte);
        }

        // Si aucune notification n'existe encore en BDD, on met une valeur par défaut pour éviter un graphe vide
        if (labels.isEmpty()) {
            labels.add("Aucune donnée");
            donnees.add(0L);
        }

        // 3. Instanciation du DTO regroupé avec données dynamiques
        DashboardStatsDTO.ChartDataDTO graphe = new DashboardStatsDTO.ChartDataDTO(labels, donnees);

        return new DashboardStatsDTO(totalAgents, totalPatients, totalNotifications, graphe);
    }
    }