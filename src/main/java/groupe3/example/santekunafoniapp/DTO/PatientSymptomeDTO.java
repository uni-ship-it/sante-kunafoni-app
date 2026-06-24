package groupe3.example.santekunafoniapp.DTO;

public class PatientSymptomeDTO {
    private Long idPatient;
    private Long idSymptome;

    public PatientSymptomeDTO() {}

    public PatientSymptomeDTO(Long idPatient, Long idSymptome) {
        this.idPatient = idPatient;
        this.idSymptome = idSymptome;
    }

    public Long getIdPatient() { return idPatient; }
    public void setIdPatient(Long idPatient) { this.idPatient = idPatient; }
    public Long getIdSymptome() { return idSymptome; }
    public void setIdSymptome(Long idSymptome) { this.idSymptome = idSymptome; }
}
