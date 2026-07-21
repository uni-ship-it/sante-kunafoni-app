package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Administrateur;
import groupe3.example.santekunafoniapp.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByTel(String tel);

}

