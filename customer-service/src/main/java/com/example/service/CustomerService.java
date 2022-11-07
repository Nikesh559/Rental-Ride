package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = repository.findByUsername(s);
        if(userDetails == null)
            throw new UsernameNotFoundException("Username or Password is incorrect");
        return userDetails;
    }

    public boolean userExists(String username) {
        UserDetails details = repository.findByUsername(username);
        return details != null;
    }

    public void save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        System.out.println(customer);
        repository.save(customer);
    }
}
