package gr.uom.RideOrCry.Specifications;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<Car> filterBy(String key, String operation, Object value) {
        return (Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> switch (operation) {
            case "gt" -> // Greater Than
                    criteriaBuilder.greaterThan(root.get(key), value.toString());
            case "gte" -> // Greater Than or Equal To
                    criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString());
            case "lt" -> // Less Than
                    criteriaBuilder.lessThan(root.get(key), value.toString());
            case "lte" -> // Less Than or Equal To
                    criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString());
            default -> {
                if (key.equals("agency")) {
                    Join<User, Car> agencyCars = root.join("agency");
                    yield criteriaBuilder.equal(agencyCars.get("afm"), value);
                }
                yield criteriaBuilder.equal(root.get(key), value);
            }
        };
    }
}
