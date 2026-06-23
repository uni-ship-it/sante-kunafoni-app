package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Entity.PatientSymptome;
import groupe3.example.santekunafoniapp.Entity.PatientSymptomeId;
import groupe3.example.santekunafoniapp.Entity.Symptome;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.Repository.PatientSymptomeRepository;
import groupe3.example.santekunafoniapp.Repository.SymptomeRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientSymptomeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientSymptomeServiceImplementation implements PatientSymptomeServiceInterface {

    private final PatientSymptomeRepository patientSymptomeRepository;
    private final PatientRepository patientRepository;
    private final SymptomeRepository symptomeRepository;

    public PatientSymptomeServiceImplementation(
            PatientSymptomeRepository patientSymptomeRepository,
            PatientRepository patientRepository,
            SymptomeRepository symptomeRepository) {
        this.patientSymptomeRepository = patientSymptomeRepository;
        this.patientRepository = patientRepository;
        this.symptomeRepository = symptomeRepository;
    }

    @Override
    public PatientSymptome ajouterSymptomeAuPatient(Long idPatient, Long idSymptome) {
        if (patientSymptomeRepository
                .existsByPatientIdUtilisateurAndSymptomeIdSymptome(idPatient, idSymptome)) {
            throw new RuntimeException("Ce symptôme est déjà assigné à ce patient");
        }

        Patient patient = patientRepository.findById(idPatient.intValue())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        Symptome symptome = symptomeRepository.findById(idSymptome)
                .orElseThrow(() -> new RuntimeException("Symptôme non trouvé"));

        return patientSymptomeRepository.save(new PatientSymptome(patient, symptome));
    }

    @Override
    public List<PatientSymptome> getSymptomesByPatient(Long patientId) {
        return patientSymptomeRepository.findByPatientIdUtilisateur(patientId);
    }

    @Override
    public List<PatientSymptome> getPatientsBySymptome(Long symptomeId) {
        return patientSymptomeRepository.findBySymptomeIdSymptome(symptomeId);
    }

    @Override
    public List<PatientSymptome> getAll() {
        return patientSymptomeRepository.findAll();
    }

    @Override
    public void supprimerSymptomeDuPatient(Long idPatient, Long idSymptome) {
        PatientSymptomeId id = new PatientSymptomeId(idPatient, idSymptome);

        if (!patientSymptomeRepository.existsById(id)) {
            throw new RuntimeException("Association patient-symptôme non trouvée");
        }

        patientSymptomeRepository.deleteById(id);
    }
}