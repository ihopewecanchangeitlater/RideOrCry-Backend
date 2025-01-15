package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping(path = "/updateQuantity/{carId}")
    public Car updateCarQuantity(@PathVariable Long carId, @RequestBody Map<String, Integer> requestBody) throws Exception {
        int quantity = requestBody.get("quantity"); // Λήψη της τιμής από το JSON αντικείμενο
        return carService.updateCarQuantity(carId, quantity);
    }

    // Εμφανίζει μια λίστα με όλα τα αμάξια
    @GetMapping(path = "/carlist")
    public List<Car> getAllCars() throws Exception {
        return carService.getAllCars();
    }
}
