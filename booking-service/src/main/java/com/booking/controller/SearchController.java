package com.booking.controller;

import com.booking.model.Message;
import com.booking.model.Transmission;
import com.booking.model.Vehicle;
import com.booking.model.VehicleStatus;
import com.booking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/explore")
public class SearchController {

    private static List<Link> searchByList;
    static {
        searchByList = new ArrayList<>();
        searchByList.add(Link.of("/cars?city={cityName}").withRel("cityName"));
        searchByList.add(Link.of("/cars?type={type}").withRel("type"));
        searchByList.add(Link.of("/cars?passengerCapacity={passengerCapacity}").withRel("passengerCapacity"));
        searchByList.add(Link.of("/cars?transmission={transmission}").withRel("transmission"));
    }

    @Autowired
    private VehicleRepository repository;



    @GetMapping("/cars")
    public ResponseEntity getCars(@RequestParam(value = "city", defaultValue = "") String city,
                                  @RequestParam(value = "type", defaultValue = "") String type ,
                                  @RequestParam(value = "passengerCapacity", defaultValue = "0") int passengerCapacity,
                                  @RequestParam(value = "transmission", defaultValue = "") Transmission transmission) {
        List<Vehicle> cars = repository.findAll();

        if(city != null && !city.isEmpty())
            cars = cars.stream().filter(carVehicle -> carVehicle.getAvailability().contains(city)).collect(Collectors.toList());
        if(type != null && !type.isEmpty())
            cars = cars.stream().filter(carVehicle -> carVehicle.getType().contains(type)).collect(Collectors.toList());
        if(passengerCapacity >= 4 && passengerCapacity <= 7)
            cars = cars.stream().filter(carVehicle -> carVehicle.getPassengerCapacity() == passengerCapacity).collect(Collectors.toList());
        if(transmission != null && transmission.toString() != "")
            cars = cars.stream().filter(carVehicle -> carVehicle.getTransmission() == transmission).collect(Collectors.toList());

        HashMap<String, List> map = new HashMap<>();
        map.put("cars", cars);
        map.put("searchBy", searchByList);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping("/car/{vehicleId}")
    public ResponseEntity getCarById(@PathVariable("vehicleId") String vehicleId) {
        Vehicle vehicle = repository.findById(vehicleId).get();
        if(vehicle == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }
}
