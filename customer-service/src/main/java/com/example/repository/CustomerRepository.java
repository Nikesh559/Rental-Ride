package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUsername(String s);
}
