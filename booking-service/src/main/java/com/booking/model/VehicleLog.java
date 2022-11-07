package com.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class VehicleLog {

    @Id
    private String bookingId;

    @Column
    private String vehicleId;

    @Column
    private String custId;

    @Column
    private String pickupLoc;

    @Column
    private String dropLoc;

    @Column
    private Date pickupDt;

    @Column
    private Date returnDt;

    @Column
    private Timestamp createDt;

    @Column
    private double charges;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPickupLoc() {
        return pickupLoc;
    }

    public void setPickupLoc(String pickupLoc) {
        this.pickupLoc = pickupLoc;
    }

    public String getDropLoc() {
        return dropLoc;
    }

    public void setDropLoc(String dropLoc) {
        this.dropLoc = dropLoc;
    }

    public Date getPickupDt() {
        return pickupDt;
    }

    public void setPickupDt(Date pickupDt) {
        this.pickupDt = pickupDt;
    }

    public Date getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(Date returnDt) {
        this.returnDt = returnDt;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    @Override
    public String toString() {
        return "VehicleLog{" +
                "bookingId='" + bookingId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", custId='" + custId + '\'' +
                ", pickupLoc='" + pickupLoc + '\'' +
                ", dropLoc='" + dropLoc + '\'' +
                ", pickupDt=" + pickupDt +
                ", returnDt=" + returnDt +
                ", createDt=" + createDt +
                ", charges=" + charges +
                '}';
    }
}
