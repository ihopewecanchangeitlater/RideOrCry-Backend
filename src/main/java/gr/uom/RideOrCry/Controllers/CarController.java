package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.Reservation;
import gr.uom.RideOrCry.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
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
        try {
            List<Car> cars = carService.searchCar(filters);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }

    // Endpoint που προσθέτει ένα αμάξι. Το αμάξι το προσθέτει στον αντιπρόσωπο (θέλει ως εισαγωγή το agencyName)
    @PostMapping(path = "/addcar/{agencyName}")
    public Car addCar(@RequestBody Car car, @PathVariable String agencyName) throws Exception {
        return carService.addCar(car, agencyName);
    }

    // Ενημερώνει το αμάξι με το συγκεκριμένο id.
    @PatchMapping(path = "/updateQuantity/{carId}")
    public Car updateCarQuantity(@PathVariable Long carId, @RequestBody Map<String, Integer> requestBody) throws Exception {
        int quantity = requestBody.get("quantity"); // Λήψη της τιμής από το JSON αντικείμενο
        return carService.updateCarQuantity(carId, quantity);
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<Reservation> bookCar(@PathVariable("id") long carId, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(carService.bookCar(carId, body.get("afm"), Date.valueOf(body.get("date")), Time.valueOf(body.get("time"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }

    @PatchMapping("/buy/{id}")
    public ResponseEntity<Car> buyCar(@PathVariable("id") int carId) {
        try {
            return ResponseEntity.ok(carService.buyCar(carId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
