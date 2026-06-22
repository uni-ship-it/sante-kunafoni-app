package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTraitement;

    private String nomTraitement;

    private LocalDate datedebut;

    private LocalDate datefin;

    private String description;

    public Traitement() {

    }

    public Long getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(Long idTraitement) {
        this.idTraitement = idTraitement;
    }

    public String getNomTraitement() {
        return nomTraitement;
    }

    public void setNomTraitement(String nomTraitement) {
        this.nomTraitement = nomTraitement;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}