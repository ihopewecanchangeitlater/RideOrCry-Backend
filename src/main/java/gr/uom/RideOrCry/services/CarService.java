package gr.uom.RideOrCry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.uom.RideOrCry.entities.Agency;
import gr.uom.RideOrCry.entities.Car;
import gr.uom.RideOrCry.repositories.AgencyRepository;
import gr.uom.RideOrCry.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    public CarService(CarRepository carRepository, AgencyRepository agencyRepository) {
        this.carRepository = carRepository;
        this.agencyRepository = agencyRepository;
    }

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
    public Car updateCar(Long carId, Car updatedCar) throws Exception {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found with ID: " + carId));
        
        // Ενημέρωση των πεδίων του υπάρχοντος αυτοκινήτου
        // Εδώ ΠΡΕΠΕΙ να έχει λάβει όλα τα πεδία (στο frontend) και να τα στέλνει ΟΛΑ στη βάση
        // Αν εισάγουμε μόνο το id και ένα πεδίο, θα μηδενήσει όλα τα άλλα (εκτός του agent)
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setFuelType(updatedCar.getFuelType());
        existingCar.setEngine(updatedCar.getEngine());
        existingCar.setSeats(updatedCar.getSeats());
        existingCar.setPrice(updatedCar.getPrice());
        existingCar.setAdditionalInfo(updatedCar.getAdditionalInfo());
        existingCar.setQuantity(updatedCar.getQuantity());

        // Αποθήκευση αλλαγών
        return carRepository.save(existingCar);
    }
    
    // Μέθοδος που λαμβάνει την λίστα όλων των αμαξιών της βάσης
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
