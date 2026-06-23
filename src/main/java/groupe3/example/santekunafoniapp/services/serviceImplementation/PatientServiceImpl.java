package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientServiceInterface {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient ajouterPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient modifierPatient(Long id, Patient patient) {

        Patient patientExistant = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        // Mettre à jour les attributs
        patientExistant.setNom(patient.getNom());
        // patientExistant.setPrenom(patient.getPrenom());
        // patientExistant.setAge(patient.getAge());
        // etc.

        return patientRepository.save(patientExistant);
    }

    @Override
    public void supprimerPatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        patientRepository.delete(patient);
    }

    @Override
    public List<Patient> afficherTousLesPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient afficherPatientParId(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));
    }
}
