package gr.uom.RideOrCry.Entities;

import gr.uom.RideOrCry.DTO.Citizen;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User citizen;

    @ManyToOne
    private Car car;

    private Date date;
    private Time time;

    public Reservation() {
    }

    public Reservation(User citizen, Car car, Date date, Time time) {
        this.citizen = citizen;
        this.car = car;
        this.date = date;
        this.time = time;
    }

    public Reservation(long id, User citizen, Car car, Date date, Time time) {
        this.id = id;
        this.citizen = citizen;
        this.car = car;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return new Citizen(citizen);
    }

    public void setCitizen(User citizen) {
        this.citizen = citizen;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
