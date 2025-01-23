package gr.uom.RideOrCry.Entities;

import gr.uom.RideOrCry.DTO.Agency;
import jakarta.persistence.*;

@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // ίσως αυτό να το κάναμε String και ονομασία να ήταν πινακίδα;

    // Σχέση πολλά αμάξια σε έναν agent
    @ManyToOne
    private User agency;

    private String brand;
    private String model;
    private String fuel;
    private int engine;
    private int seats;
    private double price;
    private int quantity;
    @Column(length = 1000)
    private String additionalInfo;

    public Car() {
    }

    public Car(long id, String brand, String model, String fuel, int engine, int seats, double price, int quantity, String additionalInfo, User agency) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.quantity = quantity;
        this.additionalInfo = additionalInfo;
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Agency getAgency() {
        return new Agency(this.agency);
    }

    public void setAgency(User agency) {
        this.agency = agency;
    }

    public boolean hasStock() {
        return this.quantity > 0;
    }

    public void buy() {
        this.quantity--;
    }

    public boolean isStockGT(int size) {
        return this.quantity > size;
    }
}