package com.example.service;

import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.model.Customer;
import com.example.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {


    @Autowired
    private BookingRepository repository;

    @Autowired
    private CustomerService customerService;

    public List<Booking> findAllBookings(String username) {
        Customer customer = (Customer)customerService.loadUserByUsername(username);
        return repository.findByCustId(customer.getCustId());
    }

    public Booking findBookingById(String username, String bookingId) {
        Customer customer = (Customer)customerService.loadUserByUsername(username);
        Booking booking = repository.findById(bookingId).get();
        if(customer.getCustId().equals(booking.getCustId()))
            return booking;
        else
            return null;
    }

    public boolean cancelBooking(String username, String bookingId) {
        Customer customer = (Customer)customerService.loadUserByUsername(username);
        Booking booking = repository.findById(bookingId).get();

        if(booking == null || !booking.getCustId().equals(customer.getCustId()) || booking.getStatus() == BookingStatus.CANCELLED)
            return false;
        else {
            booking.setStatus(BookingStatus.CANCELLED);
            repository.save(booking);
            return true;
        }
    }

    public Booking createBooking(String username, Booking booking) {
        Customer customer = (Customer)customerService.loadUserByUsername(username);
        booking.setCustId(customer.getCustId());
        booking.setCreateDt(new Date());
        booking.setStatus(BookingStatus.CONFIRMED);
        repository.save(booking);
        return booking;
    }
}
