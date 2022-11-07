package com.booking.repository;

import com.booking.model.VehicleLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleLogRepository extends JpaRepository<VehicleLog, String> {
    List<VehicleLog> findByVehicleId(String vehicleId);

    List<VehicleLog> findByCustId(String custId);
}
