package groupe3.example.santekunafoniapp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "symptome_patient")
@IdClass(PatientSymptomeId.class)
public class PatientSymptome {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Patient patient;


    @Id
    @ManyToOne
    @JoinColumn(name = "idSymptome", nullable = false)
    private Symptome symptome;


    public PatientSymptome() {}


    public PatientSymptome(Patient patient, Symptome symptome) {
        this.patient = patient;
        this.symptome = symptome;
    }


    public Patient getPatient() {
        return patient;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Symptome getSymptome() {
        return symptome;
    }


    public void setSymptome(Symptome symptome) {
        this.symptome = symptome;
    }
}