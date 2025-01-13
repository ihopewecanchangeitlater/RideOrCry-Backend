package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, String> {
}
