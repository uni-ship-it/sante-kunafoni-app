package groupe3.example.santekunafoniapp.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

public class SymptomeDTO {
    @Data
    public class Symptome{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  id;
        private String nom;
        private String description;
    }
}
