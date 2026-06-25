package groupe3.example.santekunafoniapp.Repository;


import groupe3.example.santekunafoniapp.Entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {

}
