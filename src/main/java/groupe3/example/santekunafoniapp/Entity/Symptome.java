package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class Symptome{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  id ;
        private String nom;
        private String description;
    }
