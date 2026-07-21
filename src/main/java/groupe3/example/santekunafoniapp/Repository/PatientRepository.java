package groupe3.example.santekunafoniapp.Repository;

import groupe3.example.santekunafoniapp.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Cas par localité et par maladie (alimente la carte + le graphe)
// Un "cas" = un patient distinct dont au moins un symptôme est lié à la maladie
    @Query("""
    select p.localite, m.nom, count(distinct ps.patient)
    from PatientSymptome ps
      join ps.patient p
      join SymptomeMaladie sm on sm.symptome = ps.symptome
      join sm.maladie m
    where p.localite is not null
    group by p.localite, m.nom
    """)
    List<Object[]> casParLocaliteEtMaladie();

    // Compteur "Mes symptômes"
    @Query("select count(ps) from PatientSymptome ps where ps.patient.idUtilisateur = :patientId")
    long nbSymptomesDuPatient(@Param("patientId") Long patientId);

    // Compteur "Mes épidémies" (maladies distinctes liées aux symptômes du patient)
    @Query("""
    select count(distinct sm.maladie)
    from PatientSymptome ps
      join SymptomeMaladie sm on sm.symptome = ps.symptome
    where ps.patient.idUtilisateur = :patientId
    """)
    long nbMaladiesDuPatient(@Param("patientId") Long patientId);
}

