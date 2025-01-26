package gr.uom.RideOrCry.Controllers;

import gr.uom.RideOrCry.DTO.ReservationRequest;
import gr.uom.RideOrCry.Entities.Reservation;
import gr.uom.RideOrCry.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getUserReservations() {
        List<Reservation> reservations = reservationService.getUserReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable String userId) throws Exception {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Reservation>> getCarReservations(@PathVariable Long carId) throws Exception {
        List<Reservation> reservations = reservationService.getCarReservations(carId);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) throws Exception {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long reservationId) throws Exception {
        Reservation reservation = reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(reservation);
    }
}
