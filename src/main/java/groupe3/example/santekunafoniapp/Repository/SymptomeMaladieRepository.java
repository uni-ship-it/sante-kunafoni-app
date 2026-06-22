package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.SymptomeMaladie;
import groupe3.example.santekunafoniapp.Entity.SymptomeMaladieId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomeMaladieRepository extends JpaRepository<SymptomeMaladie, SymptomeMaladieId> {

    List<SymptomeMaladie> findByMaladie_IdMaladie(Integer idMaladie);

    List<SymptomeMaladie> findBySymptome_IdSymptome(Long idSymptome);

    boolean existsById_IdSymptomeAndId_IdMaladie(Long idSymptome, Integer idMaladie);
}