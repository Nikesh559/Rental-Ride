package com.booking.model;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    private String vehicleId;

    @Column
    private String type;

    @Column
    private String model;

    @Column
    private double mileage;

    @Column
    private int passengerCapacity;

    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Column
    private double charges;

    @Column
    private String availability;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", passengerCapacity=" + passengerCapacity +
                ", transmission=" + transmission +
                ", fuelType=" + fuelType +
                ", status=" + status +
                ", charges=" + charges +
                ", availability='" + availability + '\'' +
                '}';
    }
}
