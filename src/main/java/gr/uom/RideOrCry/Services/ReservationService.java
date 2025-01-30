package gr.uom.RideOrCry.Services;

import gr.uom.RideOrCry.DTO.ReservationRequest;
import gr.uom.RideOrCry.Entities.Car;
import gr.uom.RideOrCry.Entities.Reservation;
import gr.uom.RideOrCry.Entities.User;
import gr.uom.RideOrCry.Enums.UserRole;
import gr.uom.RideOrCry.Exceptions.NoCarForTestDriveException;
import gr.uom.RideOrCry.Exceptions.NoRecordFoundException;
import gr.uom.RideOrCry.Repositories.ReservationRepository;
import gr.uom.RideOrCry.Specifications.ReservationSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CitizenService citizenService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    public Reservation createReservation(ReservationRequest reservationRequest) throws Exception {
        Car car = carService.getCarById(reservationRequest.getCarId());
        carService.checkStock(car);
        List<Reservation> carReservations = reservationRepository.findAllByCitizenAfmAndCarIdAndDate(reservationRequest.getCitizenId(), reservationRequest.getCarId(), reservationRequest.getDate());
        if (!car.isStockGT(carReservations.size())) throw new NoCarForTestDriveException("No available cars for test drive");
        User citizen = citizenService.getCitizen(reservationRequest.getCitizenId());
        Reservation reservation = new Reservation(citizen, car, reservationRequest.getDate(), reservationRequest.getTime());
        reservationRepository.saveAndFlush(reservation);
        return reservation;
    }

    public List<Reservation> getUserReservations(String user_id) throws Exception {
        User user = userService.getUserById(user_id);
        if (user.getRoles().contains(UserRole.CITIZEN))
            return reservationRepository.findAllByCitizenAfm(user_id);
        Specification<Reservation> specification = ReservationSpecification.hasAgent(user_id);
        return reservationRepository.findAll(specification);
    }

    public List<Reservation> getUserReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getCarReservations(Long carId) throws Exception {
        carService.getCarById(carId);
        Sort sort = Sort.by(Sort.Order.asc("date"), Sort.Order.asc("time"));
        return reservationRepository.findAllByCarId(carId, sort);
    }

    public Reservation deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new NoRecordFoundException("Reservation not found"));
        reservationRepository.deleteById(reservationId);
        return reservation;
    }
}
