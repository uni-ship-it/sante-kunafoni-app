package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {

}