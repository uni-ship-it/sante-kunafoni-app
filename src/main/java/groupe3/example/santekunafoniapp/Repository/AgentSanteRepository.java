package groupe3.example.santekunafoniapp.Repository;




import groupe3.example.santekunafoniapp.Entity.AgentSante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentSanteRepository
        extends JpaRepository<AgentSante, Long> {
}
