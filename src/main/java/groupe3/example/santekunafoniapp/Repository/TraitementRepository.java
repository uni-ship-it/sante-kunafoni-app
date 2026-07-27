package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {

    // 1. COMPTER LES CAS D'UNE MALADIE DEPUIS UNE DATE
    @Query("SELECT COUNT(t) FROM Traitement t " +
            "WHERE t.maladie.idMaladie = :idMaladie " +
            "AND t.datedebut >= :dateDebut")
    long countCasParMaladieDepuis(
            @Param("idMaladie") Long idMaladie,
            @Param("dateDebut") LocalDate dateDebut
    );

    // 2. RÉCUPÉRER LES TRAITEMENTS D'UNE MALADIE
    @Query("SELECT t FROM Traitement t " +
            "WHERE t.maladie.idMaladie = :idMaladie " +
            "ORDER BY t.datedebut DESC")
    List<Traitement> findByMaladieIdOrderByDatedebutDesc(
            @Param("idMaladie") Long idMaladie
    );

    // 3. COMPTER LES CAS D me MALADIE SUR UNE PÉRIODE
    @Query("SELECT COUNT(t) FROM Traitement t " +
            "WHERE t.maladie.idMaladie = :idMaladie " +
            "AND t.datedebut BETWEEN :dateDebut AND :dateFin")
    long countCasParMaladieEntreDates(
            @Param("idMaladie") Long idMaladie,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );

    // 4. RÉCUPÉRER LES CAS D'UNE MALADIE PAR DATE
    @Query("SELECT t.datedebut, COUNT(t) FROM Traitement t " +
            "WHERE t.maladie.idMaladie = :idMaladie " +
            "AND t.datedebut >= :dateDebut " +
            "GROUP BY t.datedebut " +
            "ORDER BY t.datedebut DESC")
    List<Object[]> countCasParJourDepuis(
            @Param("idMaladie") Long idMaladie,
            @Param("dateDebut") LocalDate dateDebut
    );
}