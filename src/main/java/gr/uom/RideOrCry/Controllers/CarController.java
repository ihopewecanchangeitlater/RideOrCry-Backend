package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.Models.Car;
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
}
