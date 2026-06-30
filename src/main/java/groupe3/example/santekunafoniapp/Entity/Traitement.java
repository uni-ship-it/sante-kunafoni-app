package groupe3.example.santekunafoniapp.Entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTraitement;

    private String nomTraitement;
    private LocalDate datedebut;
    private LocalDate datefin;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_maladie")
    private Maladie maladie;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "id_agent_sante")
    private AgentSante agentSante;


}