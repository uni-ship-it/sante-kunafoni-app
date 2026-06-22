package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

    @Entity
    @Data
    @Table(name="symptome")
    public class Symptome{
        @Id
        private long  Id;
        private String Nom;
        private String Description;
    }
