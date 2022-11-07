package com.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Booking {

    private String bookingId;

    private String custId;

    @NotNull
    private String vehicleId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    private Date pickupDt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    private Date returnDt;

    @NotNull
    private String pickupLoc;

    @NotNull
    private String dropLoc;

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
                ", pickupDt=" + pickupDt +
                ", returnDt=" + returnDt +
                ", pickupLoc='" + pickupLoc + '\'' +
                ", dropLoc='" + dropLoc + '\'' +
                ", status=" + status +
                '}';
    }
}
