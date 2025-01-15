package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
    // Εύρεση του agent ανάλογα με το όνομα
    Optional<Agency> findByName(String name);

    Optional<Agency> findByEmailAndPassword(String email, String password);
}
