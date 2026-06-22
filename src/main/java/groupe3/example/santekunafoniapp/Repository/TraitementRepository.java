package groupe3.example.santekunafoniapp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.example.santekunafoniapp.Entity.Traitement;


public interface TraitementRepository
        extends JpaRepository<Traitement, Long>{


}