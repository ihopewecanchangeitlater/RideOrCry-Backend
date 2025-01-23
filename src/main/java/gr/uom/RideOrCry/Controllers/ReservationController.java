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

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable String user_id) throws Exception {
        List<Reservation> reservations = reservationService.getReservations(user_id);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) throws Exception {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok(reservation);
    }

}
