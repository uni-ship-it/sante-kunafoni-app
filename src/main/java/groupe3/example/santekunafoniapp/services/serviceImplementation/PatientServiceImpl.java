package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import groupe3.example.santekunafoniapp.services.serviceInterface.PatientServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- AJOUT DE L'IMPORT
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientServiceInterface {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder; // <-- 1. AJOUT DE L'ATTRIBUT

    // 2. AJOUT DU PASSWORDENCODER DANS LE CONSTRUCTEUR POUR L'INJECTION
    public PatientServiceImpl(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Patient ajouterPatient(Patient patient) {
        // 3. HACHAGE DU MOT DE PASSE À L'INSCRIPTION
        if (patient.getMotpass() != null) {
            patient.setMotpass(passwordEncoder.encode(patient.getMotpass()));
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient modifierPatient(Long id, Patient patient) {

        Patient patientExistant = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        // Mettre à jour les attributs
        patientExistant.setNom(patient.getNom());
        patientExistant.setPrenom(patient.getPrenom());
        patientExistant.setAge(patient.getAge());
        patientExistant.setEtat(patient.getEtat());
        patientExistant.setLocalite(patient.getLocalite());
        patientExistant.setSexe(patient.getSexe());
        patientExistant.setTel(patient.getTel());
        patientExistant.setPeriode(patient.getPeriode());

        // 4. HACHAGE ÉGALEMENT SI LE MOT DE PASSE EST MODIFIÉ
        if (patient.getMotpass() != null && !patient.getMotpass().isEmpty()) {
            patientExistant.setMotpass(passwordEncoder.encode(patient.getMotpass()));
        }

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
       // ===== Dashboard =====
    @Override
public Long nombrePatients() {

    return patientRepository.count();

}
@Override
public List<Patient> getDerniersPatients() {
    return patientRepository.findTop5ByOrderByIdUtilisateurDesc();
}
@Override
public long compterHommes() {

    return patientRepository.countBySexe("Homme");
}

@Override
public long compterFemmes() {
    return patientRepository.countBySexe("Femme");
}
}
