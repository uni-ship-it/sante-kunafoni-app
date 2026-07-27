package groupe3.example.santekunafoniapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalAgentsSante;
    private long totalPatients;
    private long totalNotifications;
}
