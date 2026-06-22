package groupe3.example.santekunafoniapp.DTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Integer idNotif;
    private String titre;
    private String message;
    private LocalDateTime datePublication;
}