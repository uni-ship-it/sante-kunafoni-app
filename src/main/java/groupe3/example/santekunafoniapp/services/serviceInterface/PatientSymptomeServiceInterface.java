package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.PatientSymptome;

import java.util.List;

public interface PatientSymptomeServiceInterface {

    PatientSymptome ajouterSymptomeAuPatient(Long idPatient, Long idSymptome);

    List<PatientSymptome> getSymptomesByPatient(Long patientId);

    List<PatientSymptome> getPatientsBySymptome(Long symptomeId);

    List<PatientSymptome> getAll();

    void supprimerSymptomeDuPatient(Long idPatient, Long idSymptome);
}