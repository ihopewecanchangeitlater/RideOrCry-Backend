package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
    List<Reservation> findAllByCitizenAfmAndCarIdAndDateAndTime(String citizenAfm, long carId, Date date, Time time);

    List<Reservation> findAllByCitizenAfm(String citizenAfm);

    List<Reservation> findAllByCarId(Long carId);
}
