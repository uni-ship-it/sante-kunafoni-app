package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<patient, Integer> {
}
