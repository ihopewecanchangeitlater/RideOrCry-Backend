package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

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

    // Εμφανίζει μια λίστα με όλα τα αμάξια
    @GetMapping(path = "/carlist")
    public List<Car> getAllCars() throws Exception {
        return carService.getAllCars();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCar(@RequestParam Map<String, String> filters) {
        List<Car> cars = carService.searchCar(filters);
        return ResponseEntity.ok(cars);

    }

    // Endpoint που προσθέτει ένα αμάξι. Το αμάξι το προσθέτει στον αντιπρόσωπο (θέλει ως εισαγωγή το agencyName)
    @PostMapping(path = "/addcar/{agencyAfm}")
    public Car addCar(@RequestBody Car car, @PathVariable String agencyAfm) throws Exception {
        return carService.addCar(car, agencyAfm);
    }

    // Ενημερώνει το αμάξι με το συγκεκριμένο id.
    @PatchMapping(path = "/updateQuantity/{carId}")
    public Car updateCarQuantity(@PathVariable Long carId, @RequestBody Map<String, Integer> requestBody) throws Exception {
        int quantity = requestBody.get("quantity"); // Λήψη της τιμής από το JSON αντικείμενο
        return carService.updateCarQuantity(carId, quantity);
    }

    @PatchMapping("/buy/{id}")
    public ResponseEntity<Car> buyCar(@PathVariable("id") int carId) throws Exception {
        return ResponseEntity.ok(carService.buyCar(carId));
    }
}
