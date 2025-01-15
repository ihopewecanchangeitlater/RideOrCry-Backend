package gr.uom.RideOrCry.Specifications;

import gr.uom.RideOrCry.Entities.Car;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<Car> filterBy(String key, Object value) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value));
    }
}
