package com.example.repository;

import com.example.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByCustId(String custId);
}
