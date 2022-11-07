package com.example.controller;

import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.model.Message;
import com.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/bookings")
    public List<Booking> getUserBookings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Booking> bookings = bookingService.findAllBookings(username);
        return bookings;
    }

    @GetMapping("/booking/{bookingId}")
    public Booking getBookingById(@PathVariable("bookingId") String bookingId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return bookingService.findBookingById(username, bookingId);
    }

    @PostMapping("/booking")
    public ResponseEntity createBooking(@RequestBody Booking booking) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        booking = bookingService.createBooking(username, booking);
        return new ResponseEntity(booking.getCustId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity cancelBooking(@PathVariable("bookingId") String bookingId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(bookingService.cancelBooking(username, bookingId))
            return new ResponseEntity(new Message("Booking Cancelled", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(new Message("Booking not found or Cannot be Cancelled At this Stage", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
