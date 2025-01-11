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
    
    // Μέθοδος που λαμβάνει την λίστα όλων των αμαξιών της βάσης
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}