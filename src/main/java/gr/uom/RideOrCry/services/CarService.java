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

}
