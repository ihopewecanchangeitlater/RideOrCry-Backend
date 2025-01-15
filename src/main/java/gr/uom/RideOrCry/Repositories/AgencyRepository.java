package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
  
	Agency findByEmailAndPassword(String email, String password);
}


