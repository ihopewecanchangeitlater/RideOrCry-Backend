package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    // Εύρεση των αμαξιών της αντιπροσωπείας
    List<Car> findByAgency(Agency agency);
}
