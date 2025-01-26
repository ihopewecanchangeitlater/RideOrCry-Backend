package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Exceptions.NoRecordFoundException;
import gr.uom.RideOrCry.Repositories.AgencyRepository;
import gr.uom.RideOrCry.Repositories.CarRepository;
import gr.uom.RideOrCry.Specifications.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AgencyRepository agencyRepository;

    // Μέθοδος προσθήκης αμαξιού.
    // Βρίσκει το όνομα του agent και θέτει στο αμάξι το όνομα του agent πριν το αποθηκεύσει στην βάση
    public Car addCar(Car car, String agencyAfm) throws Exception {
        User agency = agencyRepository.findById(agencyAfm).orElseThrow(() -> new NoRecordFoundException("Agency not found with id: " + agencyAfm));
        car.setAgency(agency);
        return carRepository.save(car);
    }

    // Μέθοδος που λαμβάνει τα αμάξια ανάλογα με το όνομα του agent για να τα εμφανίσει
    public List<Car> getCarsByAgency(String agencyName) throws Exception {
        User agency = agencyRepository.findAgencyByName(agencyName).orElseThrow(() -> new NoRecordFoundException("Agency not found with name: " + agencyName));
        return carRepository.findByAgency(agency);
    }

    // Μέθοδος που λαμβάνει το αμάξι ανάλογα με το id του
    public Car getCarById(Long carId) throws Exception {
        return carRepository.findById(carId).orElseThrow(() -> new NoRecordFoundException("Car not found with ID: " + carId));
    }

    // Μέθοδος που ενημερώνει το αμάξι (με βάση το id του) στην βάση
    public Car updateCarQuantity(Long carId, int quantity) throws Exception {
        Car existingCar = carRepository.findById(carId).orElseThrow(() -> new NoRecordFoundException("Car not found with ID: " + carId));
        // Ενημέρωση μόνο του quantity
        existingCar.setQuantity(quantity);
        // Αποθήκευση αλλαγών
        return carRepository.save(existingCar);
    }

    // Μέθοδος που λαμβάνει την λίστα όλων των αμαξιών της βάσης
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    private String extractOperation(String key) {
        if (key.endsWith("_gt")) return "gt";
        if (key.endsWith("_gte")) return "gte";
        if (key.endsWith("_lt")) return "lt";
        if (key.endsWith("_lte")) return "lte";
        return "eq"; // Default to equal
    }

    private String extractField(String key) {
        return key.replaceAll("_(gt|gte|lt|lte)$", ""); // Remove the operation suffix
    }

    public List<Car> searchCar(Map<String, String> filters) {
        Specification<Car> spec = Specification.where(null);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String operation = extractOperation(key);
            String field = extractField(key);
            String value = filter.getValue();
            spec = spec.and(CarSpecification.filterBy(field, operation, value));
        }
        Sort sort = Sort.by(Sort.Order.asc("brand"), Sort.Order.asc("model"), Sort.Order.asc("engine"));
        return carRepository.findAll(spec, sort);
    }

    public Car buyCar(long carId) throws Exception {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoRecordFoundException(""));
        if (!car.hasStock()) throw new Exception("");
        car.buy();
        carRepository.saveAndFlush(car);
        return car;
    }
}
