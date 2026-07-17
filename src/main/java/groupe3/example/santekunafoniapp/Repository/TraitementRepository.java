package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {

    // Compte les cas d'une maladie depuis une date donnée
    // Utilisé par verifierEpidemie pour détecter un seuil épidémique
    @Query("SELECT COUNT(t) FROM Traitement t " +
            "WHERE t.maladie.idMaladie = :idMaladie " +
            "AND t.datedebut >= :dateDebut")
    long countCasParMaladieDepuis(
            @Param("idMaladie") Long idMaladie,
            @Param("dateDebut") LocalDate dateDebut
    );
}