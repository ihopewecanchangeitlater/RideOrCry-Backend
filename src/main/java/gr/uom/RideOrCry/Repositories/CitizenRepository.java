package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
  
	Citizen findByEmailAndPassword(String email, String password);
}
