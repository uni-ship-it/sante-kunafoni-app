package groupe3.example.santekunafoniapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalAgentsSante;
    private long totalPatients;
    private long totalNotifications;
    private ChartDataDTO grapheAlertes;

    // Classe interne statique simplifiée avec Lombok
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChartDataDTO {
        private List<String> labels;
        private List<Long> donnees;
    }
}
