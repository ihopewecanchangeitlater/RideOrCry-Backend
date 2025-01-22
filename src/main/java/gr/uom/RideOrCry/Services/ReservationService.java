package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.DTO.ReservationRequest;
import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.Citizen;
import gr.uom.RideOrCry.Entities.Reservation;
import gr.uom.RideOrCry.Repositories.ReservationRepository;
import gr.uom.RideOrCry.Specifications.ReservationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CitizenService citizenService;
    @Autowired
    private CarService carService;

    public boolean isAvailable(Car car, String citizen_afm, Date date, Time time) {
        List<Reservation> reservations = reservationRepository.findAllByCitizenAfmAndCarIdAndDateAndTime(citizen_afm, car.getId(), date, time);
        return car.isStockGT(reservations.size());
    }

    public Reservation createReservation(ReservationRequest reservationRequest) {
        try {
            Car car = carService.getCarById(reservationRequest.getCarId());
            Citizen citizen = citizenService.getCitizen(reservationRequest.getCitizenId());
            Reservation reservation = new Reservation(citizen, car, reservationRequest.getDate(), reservationRequest.getTime());
            reservationRepository.saveAndFlush(reservation);
            return reservation;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Reservation> getReservations(String user_id, Boolean is_agent) {
        if (!is_agent) return reservationRepository.findAllByCitizenAfm(user_id);
        Specification<Reservation> specification = ReservationSpecification.hasAgent(user_id);
        return reservationRepository.findAll(specification);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

//    public Reservation createReservation(Car car, String afm, Date date, Time time) {
//        if (!isAvailable(car, afm, date, time)) return null;
//        try {
//            Citizen citizen = citizenService.getCitizen(afm);
//            if (Objects.isNull(citizen)) throw new Exception("No citizen found");
//            Reservation reservation = new Reservation(citizen, car, date, time);
//            reservationRepository.saveAndFlush(reservation);
//            return reservation;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
}
