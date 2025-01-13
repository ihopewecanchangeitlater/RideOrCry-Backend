package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Models.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {
}
