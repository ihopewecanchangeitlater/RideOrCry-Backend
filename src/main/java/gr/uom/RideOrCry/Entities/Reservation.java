package gr.uom.RideOrCry.Entities;

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
    private Citizen citizen;

    @ManyToOne
    private Car car;

    private Date date;
    private Time time;

    public Reservation() {
    }

    public Reservation(Citizen citizen, Car car, Date date, Time time) {
        this.citizen = citizen;
        this.car = car;
        this.date = date;
        this.time = time;
    }

    public Reservation(long id, Citizen citizen, Car car, Date date, Time time) {
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
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
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
