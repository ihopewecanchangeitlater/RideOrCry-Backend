package gr.uom.RideOrCry.Models;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private String model;
    private String fuel;
    private int engine;
    private int seats;
    private double price;
    private String additionalInformation;
    private int stock;
    @ManyToOne
    private Dealer dealer;

    public Car() {
    }

    public Car(String brand, String model, String fuel, int engine, int seats, double price, String additionalInformation, int stock, Dealer dealer) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.additionalInformation = additionalInformation;
        this.stock = stock;
        this.dealer = dealer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public boolean hasStock() {
        return this.stock > 0;
    }

    public void buy() {
        this.stock--;
    }

    public boolean isStockGT(int size) {
        return this.stock > size;
    }
}
