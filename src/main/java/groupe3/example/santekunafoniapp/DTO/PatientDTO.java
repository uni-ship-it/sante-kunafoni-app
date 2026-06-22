package groupe3.example.santekunafoniapp.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private String localite;
    private int age;
    private String etat;
    private String sexe;
    private Date periode;
}
