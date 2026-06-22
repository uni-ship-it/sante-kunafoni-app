package groupe3.example.santekunafoniapp.services.serviceInterface;

import groupe3.example.santekunafoniapp.Entity.Patient;

import java.util.List;

public interface PatientServiceInterface {
    Patient ajouterPatient(Patient patient);

    Patient modifierPatient(int id, Patient patient);

    void supprimerPatient(int id);

    List<Patient> afficherTousLesPatients();

    Patient afficherPatientParId(int id);
}
