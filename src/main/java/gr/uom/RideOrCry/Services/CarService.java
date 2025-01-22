package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Repositories.AgencyRepository;
import gr.uom.RideOrCry.Repositories.CarRepository;
import gr.uom.RideOrCry.Specifications.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AgencyRepository agencyRepository;
//    @Autowired
//    private ReservationService reservationService;

    // Μέθοδος προσθήκης αμαξιού.
    // Βρίσκει το όνομα του agent και θέτει στο αμάξι το όνομα του agent πριν το αποθηκεύσει στην βάση
    public Car addCar(Car car, String agencyAfm) throws Exception {
        Agency agency = agencyRepository.findById(agencyAfm)
                .orElseThrow(() -> new Exception("Agency not found with id: " + agencyAfm));
        car.setAgency(agency);
        return carRepository.save(car);
    }

    // Μέθοδος που λαμβάνει τα αμάξια ανάλογα με το όνομα του agent για να τα εμφανίσει
    public List<Car> getCarsByAgency(String agencyName) throws Exception {
        Agency agency = agencyRepository.findByName(agencyName)
                .orElseThrow(() -> new Exception("Agency not found with name: " + agencyName));
        return carRepository.findByAgency(agency);
    }

    // Μέθοδος που λαμβάνει το αμάξι ανάλογα με το id του
    public Car getCarById(Long carId) throws Exception {
        return carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found with ID: " + carId));
    }

    // Μέθοδος που ενημερώνει το αμάξι (με βάση το id του) στην βάση
    public Car updateCarQuantity(Long carId, int quantity) throws Exception {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found with ID: " + carId));

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
        return carRepository.findAll(spec);
    }

//    public Reservation bookCar(long carId, String afm, Date date, Time time) {
//        Optional<Car> optionalCar = carRepository.findById(carId);
//        Car car = optionalCar.orElse(null);
//        if (Objects.isNull(car)) return null;
//        return reservationService.createReservation(car, afm, date, time);
//    }

    public Car buyCar(long carId) {
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
