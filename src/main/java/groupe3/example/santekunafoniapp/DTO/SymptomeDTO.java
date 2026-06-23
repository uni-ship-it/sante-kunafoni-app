package groupe3.example.santekunafoniapp.DTO;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

public class SymptomeDTO {
    @Data
    public class Symptome{
        private long  id;
        private String nom;
        private String description;
    }
}
