package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Models.Car;
import gr.uom.RideOrCry.Repositories.CarRepository;
import gr.uom.RideOrCry.Specifications.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public boolean isAvailable(Car car) {
        return car.hasStock();
    }

    public List<Car> searchCar(Map<String, String> filters) {
        Specification<Car> spec = Specification.where(null);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            spec = spec.and(CarSpecification.filterBy(filter.getKey(), filter.getValue()));
        }
        return carRepository.findAll(spec);
    }

    public Car buyCar(int carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) return null;
        Car car = optionalCar.get();
        if (!car.hasStock()) return null;
        try {
            car.buy();
            carRepository.saveAndFlush(car);
            return car;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
