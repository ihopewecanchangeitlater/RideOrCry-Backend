package gr.uom.RideOrCry.repositories;

import gr.uom.RideOrCry.entities.Agency;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
    // Εύρεση του agent ανάλογα με το όνομα
	Optional<Agency> findByName(String name);
}
