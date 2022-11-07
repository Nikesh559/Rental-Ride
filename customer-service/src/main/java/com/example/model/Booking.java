package com.example.model;

import com.example.model.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {
    @Id
    private String bookingId;

    @Column
    private String custId;

    @Column
    private String vehicleId;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    private Date createDt;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    private Date returnDt;

    @Column
    private String pickupLoc;

    @Column
    private String dropLoc;

    @Column
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(Date returnDt) {
        this.returnDt = returnDt;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "bookingId='" + bookingId + '\'' +
                ", custId='" + custId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", createDt=" + createDt +
                ", returnDt=" + returnDt +
                ", pickupLoc='" + pickupLoc + '\'' +
                ", dropLoc='" + dropLoc + '\'' +
                ", status=" + status +
                '}';
    }
}
