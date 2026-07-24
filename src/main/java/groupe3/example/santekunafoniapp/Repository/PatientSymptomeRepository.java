package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.PatientSymptome;
import groupe3.example.santekunafoniapp.Entity.PatientSymptomeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientSymptomeRepository extends JpaRepository<PatientSymptome, PatientSymptomeId> {

    @Query("SELECT COUNT(ps) > 0 FROM PatientSymptome ps " +
            "WHERE ps.patient.idUtilisateur = :idPatient " +
            "AND ps.symptome.id = :idSymptome")
    boolean existsByPatientAndSymptome(
            @Param("idPatient") Long idPatient,
            @Param("idSymptome") Long idSymptome
    );

    List<PatientSymptome> findByPatientIdUtilisateur(Long patientId);

    List<PatientSymptome> findBySymptomeId(Long symptomeId);
}