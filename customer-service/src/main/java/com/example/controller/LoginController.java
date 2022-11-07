package com.example.controller;

import com.example.dto.UserDTO;
import com.example.model.Customer;
import com.example.model.Message;
import com.example.service.CustomerService;
import com.example.util.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customer")
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomerService service;

    @Autowired
    private ModelMapper modelMapper;

    private static final String SECRET_KEY = "3548870d-1e94-490c-bb33-ecd182b487f1";

    @PostMapping("/login")
    public ResponseEntity authenticate(Authentication auth, HttpServletResponse response) {

        Map<String, Object> claims = new HashMap();
        claims.put("username", auth.getName());
        claims.put("role", auth.getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority());
        final String jwtToken = Jwts.builder().
                setClaims(claims).
                setSubject(auth.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        response.setHeader("JWT_TOKEN", jwtToken);

        Message message = new Message("Logged In Successfully", HttpStatus.OK);
//        Link link = linkTo(methodOn(LoginController.class).getCustomerDetails(auth.getName())).withRel("userInfo");
//        message.setInfo(link);

        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Customer customer, BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(service.userExists(customer.getUsername())) {
            return new ResponseEntity(new Message("Username Already exists", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        customer.setRole("USER");
        customer.setCustId(UUID.randomUUID().toString());
        service.save(customer);
        Message msg = new Message("User registered successfully", HttpStatus.CREATED);

        return new ResponseEntity(msg,HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity getCustomerDetails(@PathVariable("username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getName().equals(username)) {
            return new ResponseEntity(new Message("Access Forbidden", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
        }
        Customer customer = (Customer) service.loadUserByUsername(username);
        UserDTO userDTO = modelMapper.map(customer, UserDTO.class);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }
}
