package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.DashboardStatsDTO;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import groupe3.example.santekunafoniapp.Repository.NotificationRepository;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdminDashboardServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        // 1. Récupération des compteurs principaux
        long totalAgents = agentSanteRepository.count();
        long totalPatients = patientRepository.count();
        long totalNotifications = notificationRepository.count();

        // 2. Préparation des données du graphique d'alertes
        List<String> mois = List.of("Jan", "Fév", "Mar", "Avr", "Mai", "Juin");
        List<Long> valeursAlertes = List.of(12L, 25L, 18L, 32L, 20L, 40L);

        // 3. Instanciation de la classe interne de votre DTO regroupé
        DashboardStatsDTO.ChartDataDTO graphe = new DashboardStatsDTO.ChartDataDTO(
                mois, valeursAlertes);
        // 4. Renvoi du DTO complet
        return new DashboardStatsDTO(totalAgents, totalPatients, totalNotifications, graphe);
    }
}
