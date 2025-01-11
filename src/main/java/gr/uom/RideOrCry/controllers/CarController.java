package gr.uom.RideOrCry.controllers;

import org.springframework.web.bind.annotation.*;

import gr.uom.RideOrCry.entities.Car;
import gr.uom.RideOrCry.services.CarService;

import java.util.List;

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