package groupe3.example.santekunafoniapp.Entity;

import java.io.Serializable;
import java.util.Objects;

public class PatientSymptomeId implements Serializable {

    private Long patient;
    private Long symptome;

    public PatientSymptomeId() {}

    public PatientSymptomeId(Long patient, Long symptome) {
        this.patient = patient;
        this.symptome = symptome;
    }

    public Long getPatient() { return patient; }
    public void setPatient(Long patient) { this.patient = patient; }
    public Long getSymptome() { return symptome; }
    public void setSymptome(Long symptome) { this.symptome = symptome; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientSymptomeId)) return false;
        PatientSymptomeId that = (PatientSymptomeId) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(symptome, that.symptome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, symptome);
    }
}
