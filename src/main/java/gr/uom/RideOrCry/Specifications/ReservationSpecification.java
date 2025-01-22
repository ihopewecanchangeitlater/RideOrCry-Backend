package gr.uom.RideOrCry.Specifications;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.Reservation;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ReservationSpecification {
    public static Specification<Reservation> hasAgent(String agent_id) {
        return (root, query, criteriaBuilder) -> {
            Join<Car, Reservation> carReservations = root.join("car");
            return criteriaBuilder.equal(carReservations.get("agency").get("afm"), agent_id);
        };
    }
}
