package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.PatientSymptome;
import groupe3.example.santekunafoniapp.Entity.PatientSymptomeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientSymptomeRepository
        extends JpaRepository<PatientSymptome, PatientSymptomeId> {

    List<PatientSymptome> findByPatientIdUtilisateur(Long patientId);
    List<PatientSymptome> findBySymptomeIdSymptome(Long symptomeId);
    boolean existsByPatientIdUtilisateurAndSymptomeIdSymptome(Long patientId, Long symptomeId);
}
