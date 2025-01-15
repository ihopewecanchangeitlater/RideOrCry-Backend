package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Models.Car;
import gr.uom.RideOrCry.Models.Reservation;
import gr.uom.RideOrCry.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarService carService;

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

    @PatchMapping("/book/{id}")
    public ResponseEntity<Reservation> bookCar(@PathVariable("id") int carId, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(carService.bookCar(carId, body.get("ssn"), Date.valueOf(body.get("date")), Time.valueOf(body.get("time"))));
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
