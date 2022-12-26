# Rental Ride
It has functionality to explore cars options and make booking. Additional admin access to review car logs and customer booking history.

https://user-images.githubusercontent.com/59741887/200472373-27fb7a10-5f09-48db-975c-a27cf5470852.mp4

# Table of Contents
* Technologies used 
* Features
* Project Overview
* Project Architecture

# Technologies used
* Java
* Spring Boot
* Spring Security
* API Gateway
* Netflix Eureka

# Features
 - Used API Gateway for dynamic routing, filtering and security.
 - Implemented Authentication mechanism using Spring Security and JWT. 
 - Used BCryptPasswordEncoder for hashing the password.
 - Used Netflix Eureka for microservices discovery.


# Project Architecture
<img src="https://user-images.githubusercontent.com/59741887/200380408-9a9fa591-3765-41b7-a86c-9e9615662382.PNG" width="600" height="450"/>


###  Customer Service
Performs customer registration, authentication and stores bookings information of customers. 
Method	| Path	| Description	| Role |
------------- | ------------------------- | ------------- | ---------
POST | /customer/register | Register Customer | USER |
POST | /customer/login | Login into account | USER |
GET	| /customer/{custId}	| Get Customer Details | USER | 
GET	| /customer/bookings	| Get all bookings of customer | USER | 
GET	| /product/booking/{bookingId}	| Get booking by bookingId	| USER | 
GET | /logout | Logout of account | USER/ADMIN | 
 
### Booking Service
Used for booking cars and cancellation, maintaining records of cars in stock and its history. 

Method	| Path	| Description	| ROLE
------------- | ------------------------- | ------------- | ------------
GET | /explore/cars | Get all cars records | USER
GET | /explore/car/{carId} | Get car record by id | USER
POST	| /book	| Book a car | USER
DELETE | booking/{bookingId} | Delete booking | USER
GET	| /logs/cars/{carId}	| Get Car history	 | ADMIN
GET	| /logs/customer/{customer}	| Get Customer booking history	| ADMIN







