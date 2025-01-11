package gr.uom.RideOrCry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.entities.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Εύρεση των αμαξιών της αντιπροσωπείας
	List<Car> findByAgency(Agency agency);
}
