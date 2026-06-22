package groupe3.example.santekunafoniapp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table (name = "maladie")
@Getter
@Setter
@NoArgsConstructor

public class Maladie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_maladie")
    private Long idMaladie;

    @Column(nullable = false,unique = true)
    private String nom;

    @Column(nullable = false , length = 255)
    private String description ;


    @Column(name = "date_declaration")
    private LocalDate dateDeclaration;

    public void setDescription(Object description) {

    }

    public void setDateDeclaration(Object dateDeclaration) {

    }

    public void setNom(Object nom) {

    }

    public Object getDateDeclaration() {
        return null;
    }

    public Object getDescription() {
            return null;
    }
}
