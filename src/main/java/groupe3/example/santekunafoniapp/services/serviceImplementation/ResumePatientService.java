package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.StatistiqueMaladieDTOs.ResumePatientDTO;
import groupe3.example.santekunafoniapp.Entity.Patient;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumePatientService {

    // Nombre de cas dans la localité qui déclenche le bandeau d'alerte.
    // Mettez 1 pour tester, remontez à 50 (ou autre) ensuite.
    private static final long SEUIL_ALERTE = 1;

    private final PatientRepository patientRepository;
    private final StatistiqueMaladieService statistiqueMaladieService;

    public ResumePatientService(PatientRepository patientRepository,
                                StatistiqueMaladieService statistiqueMaladieService) {
        this.patientRepository = patientRepository;
        this.statistiqueMaladieService = statistiqueMaladieService;
    }

    public ResumePatientDTO resume(Long patientId) {
        Patient p = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient introuvable : " + patientId));

        long nbMaladies = patientRepository.nbMaladiesDuPatient(patientId);
        long nbSymptomes = patientRepository.nbSymptomesDuPatient(patientId);

        String alerte = statistiqueMaladieService.maladiesParLocalite().stream()
                .filter(e -> e.localite().equalsIgnoreCase(p.getLocalite()))
                .findFirst()
                .filter(e -> e.totalCas() >= SEUIL_ALERTE)
                .map(e -> "Cas de " + e.maladieDominante().toLowerCase()
                        + " signalés à " + e.localite()
                        + ". Lavez-vous les mains régulièrement et buvez de l'eau potable traitée.")
                .orElse(null);

        return new ResumePatientDTO(p.getPrenom(), p.getLocalite(), nbMaladies, nbSymptomes, alerte);
    }
}