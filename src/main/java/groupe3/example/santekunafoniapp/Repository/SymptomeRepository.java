package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Symptome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.ScopedValue;
import java.util.List;

@Repository

public interface SymptomeRepository extends JpaRepository<Symptome, Long> {

    Symptome save(Symptome symptome, Long id);

    <T> ScopedValue<T> findById();

    void deleteSymptomeById(long id);
}
