package com.booking.dto;

import com.booking.model.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookingDTO {
    private String bookingId;

    private String custId;

    @NotNull
    private String vehicleId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MMM-YY")
    private String pickupDt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MMM-YY")
    private String returnDt;

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

    public String getPickupDt() {
        return pickupDt;
    }

    public void setPickupDt(String pickupDt) {
        this.pickupDt = pickupDt;
    }

    public String getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(String returnDt) {
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
