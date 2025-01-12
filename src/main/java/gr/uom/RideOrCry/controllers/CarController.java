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

    // Endpoint που εμφανίζει τα αμάξια του αντιπρόσωπου (θέλει ως εισαγωγή το agencyName)
    @GetMapping(path = "/byagency/{agencyName}")
    public List<Car> getCarsByAgency(@PathVariable String agencyName) throws Exception {
        return carService.getCarsByAgency(agencyName);
    }

    // Εμφανίζει το αμάξι με το συγκεκριμένο id (μπορεί να χρησιμοποιηθεί για το update)
    @GetMapping(path = "/{carId}")
    public Car getCarById(@PathVariable Long carId) throws Exception {
        return carService.getCarById(carId);
    }

    // Ενημερώνει το αμάξι με το συγκεκριμένο id.
    // ΔΕΝ ΕΝΗΜΕΡΩΝΕΙ ΟΛΑ ΤΑ ΠΕΔΙΑ. ΑΝ ΑΛΛΑΞΕΙΣ ΕΝΑ ΠΕΔΙΟ ΤΟΤΕ ΤΑ ΑΛΛΑ ΓΙΝΟΝΤΑΙ ΚΕΝΑ.
    @PatchMapping(path = "/updateQuantity/{carId}")
    public Car updateCarQuantity(@PathVariable Long carId, @RequestBody int quantity) throws Exception {
        return carService.updateCarQuantity(carId, quantity);
    }
    
    // Εμφανίζει μια λίστα με όλα τα αμάξια
    @GetMapping(path = "/carlist")
    public List<Car> getAllCars() throws Exception {
        return carService.getAllCars();
    }
}
