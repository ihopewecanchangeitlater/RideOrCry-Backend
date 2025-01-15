package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Entities.Agency;
import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.Reservation;
import gr.uom.RideOrCry.Repositories.AgencyRepository;
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
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private ReservationService reservationService;

    // Μέθοδος προσθήκης αμαξιού.
    // Βρίσκει το όνομα του agent και θέτει στο αμάξι το όνομα του agent πριν το αποθηκεύσει στην βάση
    public Car addCar(Car car, String agencyName) throws Exception {
        Agency agency = agencyRepository.findByName(agencyName)
                .orElseThrow(() -> new Exception("Agency not found with name: " + agencyName));
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

    public List<Car> searchCar(Map<String, String> filters) {
        Specification<Car> spec = Specification.where(null);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            spec = spec.and(CarSpecification.filterBy(filter.getKey(), filter.getValue()));
        }
        return carRepository.findAll(spec);
    }

    public Reservation bookCar(long carId, String afm, Date date, Time time) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Car car = optionalCar.orElse(null);
        if (Objects.isNull(car)) return null;
        return reservationService.createReservation(car, afm, date, time);
    }

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
