package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.DashboardStatsDTO;
import groupe3.example.santekunafoniapp.Repository.AgentSanteRepository;
import groupe3.example.santekunafoniapp.Repository.NotificationRepository;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.AdminDashboardServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardServiceInterface {
    //Declaration des variables
        private final AgentSanteRepository agentSanteRepository;
        private final PatientRepository patientRepository;
        private final NotificationRepository notificationRepository;

    //constructor
    public AdminDashboardServiceImpl(AgentSanteRepository agentSanteRepository, PatientRepository patientRepository, NotificationRepository notificationRepository)
    {
        this.agentSanteRepository = agentSanteRepository;
        this.patientRepository = patientRepository;
        this.notificationRepository = notificationRepository;
    }

    //implemenation de l'interface
    @Override
    public DashboardStatsDTO getDashboardStatsDTO() {
        long countAgents = agentSanteRepository.count();
        long countPatients = patientRepository.count();
        long countNotifications = notificationRepository.count();

        return new DashboardStatsDTO(countAgents, countPatients,countNotifications);
    }
}
