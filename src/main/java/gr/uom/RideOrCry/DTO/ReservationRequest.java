package gr.uom.RideOrCry.DTO;

import java.sql.Date;
import java.sql.Time;

public class ReservationRequest {
    private long carId;
    private String citizenId;
    private Date date;
    private Time time;

    public ReservationRequest() {
    }

    public ReservationRequest(long carId, String citizenId, Date date, Time time) {
        this.carId = carId;
        this.citizenId = citizenId;
        this.date = date;
        this.time = time;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
