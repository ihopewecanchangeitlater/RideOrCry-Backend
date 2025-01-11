package gr.uom.RideOrCry.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uom.RideOrCry.entities.Car;
import gr.uom.RideOrCry.services.CarService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cars")
public class CarController {
	
	// Carservice 
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Endpoint που προσθέτει ένα αμάξι. Το αμάξι το προσθέτει στον αντιπρόσωπο (θέλει ως εισαγωγή το agencyName)
    @PostMapping(path = "/addcar/{agencyName}")
    public Car addCar(@RequestBody Car car, @PathVariable String agencyName) throws Exception {
        return carService.addCar(car, agencyName);
    }
    
    // Εμφανίζει μια λίστα με όλα τα αμάξια
    @GetMapping(path = "/carlist")
    public List<Car> getAllCars() throws Exception {
        return carService.getAllCars();
    }
}