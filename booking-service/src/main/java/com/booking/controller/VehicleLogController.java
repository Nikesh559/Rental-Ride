package com.booking.controller;

import com.booking.model.Vehicle;
import com.booking.model.VehicleLog;
import com.booking.repository.VehicleLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/log")
public class VehicleLogController {

    @Autowired
    private VehicleLogRepository repository;

    @GetMapping("/car/{vehicleId}")
    public List<VehicleLog> getVehicleLogById(@PathVariable("vehicleId") String vehicleId) {
        return repository.findByVehicleId(vehicleId);
    }

    @GetMapping("/customer/{custId}")
    public List<VehicleLog> getVehicleLogByCustId(@PathVariable("custId") String custId) {
        return repository.findByCustId(custId);
    }
}
