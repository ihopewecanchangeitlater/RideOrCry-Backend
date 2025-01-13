package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.Models.Car;
import gr.uom.RideOrCry.Models.Citizen;
import gr.uom.RideOrCry.Models.Reservation;
import gr.uom.RideOrCry.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CitizenService citizenService;

    public boolean isAvailable(Car car, String citizen_ssn, Date date, Time time) {
        List<Reservation> reservations = reservationRepository.findByCitizenSsnAndCarIdAndDateAndTime(citizen_ssn, car.getId(), date, time);
        return car.isStockGT(reservations.size());
    }

    public Reservation createReservation(Car car, String ssn, Date date, Time time) {
        if (!isAvailable(car, ssn, date, time)) return null;
        try {
            Citizen citizen = citizenService.getCitizen(ssn);
            if (Objects.isNull(citizen)) throw new Exception("No citizen found");
            Reservation reservation = new Reservation(citizen, car, date, time);
            reservationRepository.saveAndFlush(reservation);
            return reservation;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
