package groupe3.example.santekunafoniapp.services.serviceImplementation;

import groupe3.example.santekunafoniapp.DTO.StatistiqueMaladieDTOs.*;
import groupe3.example.santekunafoniapp.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatistiqueMaladieService {

    private final PatientRepository patientRepository;

    public StatistiqueMaladieService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<MaladieLocaliteDTO> maladiesParLocalite() {
        List<Object[]> lignes = patientRepository.casParLocaliteEtMaladie();

        // localite -> (maladie -> nombre de cas)
        Map<String, Map<String, Long>> parLocalite = new LinkedHashMap<>();
        for (Object[] l : lignes) {
            String localite = String.valueOf(l[0]);
            String maladie = String.valueOf(l[1]);
            long cas = ((Number) l[2]).longValue();
            parLocalite.computeIfAbsent(localite, x -> new LinkedHashMap<>())
                    .merge(maladie, cas, Long::sum);
        }

        List<MaladieLocaliteDTO> resultat = new ArrayList<>();
        for (var entree : parLocalite.entrySet()) {
            Map<String, Long> parMaladie = entree.getValue();

            String dominante = parMaladie.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Inconnue");

            long total = parMaladie.values().stream().mapToLong(Long::longValue).sum();

            List<CasMaladieDTO> detail = parMaladie.entrySet().stream()
                    .map(m -> new CasMaladieDTO(m.getKey(), m.getValue()))
                    .sorted(Comparator.comparingLong(CasMaladieDTO::cas).reversed())
                    .toList();

            resultat.add(new MaladieLocaliteDTO(entree.getKey(), dominante, total, detail));
        }

        resultat.sort(Comparator.comparingLong(MaladieLocaliteDTO::totalCas).reversed());
        return resultat;
    }
}