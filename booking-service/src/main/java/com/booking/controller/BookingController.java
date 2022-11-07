package com.booking.controller;

import com.booking.dto.BookingDTO;
import com.booking.model.*;
import com.booking.repository.VehicleLogRepository;
import com.booking.repository.VehicleRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
public class BookingController {

    @Autowired
    private Gson gson;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private VehicleLogRepository logRepository;

    @PostMapping("/book")
    public ResponseEntity bookVehicle(@RequestBody @Valid Booking booking, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Vehicle vehicle = repository.findById(booking.getVehicleId()).get();

        if(vehicle == null)
            return new ResponseEntity(new Message("Car not found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);

        if(vehicle.getStatus() == VehicleStatus.BOOKED)
            return new ResponseEntity(new Message("Car Already Booked", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

        booking.setBookingId(UUID.randomUUID().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.set("JWT_TOKEN", request.getHeader("JWT_TOKEN"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        BookingDTO bookingDTO = mapper.map(booking, BookingDTO.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookingDTO.setPickupDt(simpleDateFormat.format(booking.getPickupDt()));
        bookingDTO.setReturnDt(simpleDateFormat.format(booking.getReturnDt()));
        HttpEntity entity = new HttpEntity(gson.toJson(bookingDTO), headers);

        System.out.println(gson.toJson(bookingDTO));
        String custId = restTemplate.postForObject("lb://customer-service/customer/booking", entity, String.class);
        booking.setCustId(custId);

        vehicle.setStatus(VehicleStatus.BOOKED);
        repository.save(vehicle);

        VehicleLog log = new VehicleLog();
        log.setVehicleId(vehicle.getVehicleId());
        log.setBookingId(booking.getBookingId());
        log.setCustId(booking.getCustId());
        log.setBookingId(booking.getPickupLoc());
        log.setPickupDt(booking.getPickupDt());
        log.setPickupLoc(booking.getPickupLoc());
        log.setReturnDt(booking.getReturnDt());
        log.setDropLoc(booking.getDropLoc());
        log.setCreateDt(new Timestamp(System.currentTimeMillis()));
        log.setCharges(vehicle.getCharges());
        log.setBookingId(booking.getBookingId());
        logRepository.save(log);

        return new ResponseEntity(log, HttpStatus.CREATED);
    }

}
