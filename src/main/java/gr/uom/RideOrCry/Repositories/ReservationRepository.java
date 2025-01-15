package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCitizenSsnAndCarIdAndDateAndTime(Long citizenSsn, long carId, Date date, Time time);
}
