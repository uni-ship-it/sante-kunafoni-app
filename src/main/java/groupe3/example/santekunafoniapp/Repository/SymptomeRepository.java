package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Symptome;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomeRepository extends JpaRepository<Symptome, Long> {

    // Recherche un symptôme par son nom exact
    Optional<Symptome> findByNom(String nom);

    // Recherche les symptômes contenant un mot-clé (ex: pour une barre de recherche)
    List<Symptome> findByNomContainingIgnoreCase(String nom);
}