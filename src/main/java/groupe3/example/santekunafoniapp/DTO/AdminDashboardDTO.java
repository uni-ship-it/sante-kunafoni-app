package groupe3.example.santekunafoniapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardDTO {
    private long totalAgentsSante;
    private long totalPatients;
    private long totalAlertes;
}
