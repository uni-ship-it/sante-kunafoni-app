package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Notification;
import groupe3.example.santekunafoniapp.Entity.Symptome;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository

public interface SymptomeRepository extends JpaRepository<Symptome, Long> {

    // Symptome save(Symptome symptome, Long id);

    // Optional<Notification> findById(Long id);


    // void deleteSymptomeById(long id);
}
