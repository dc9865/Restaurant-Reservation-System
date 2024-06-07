package com.dancodes.restaurantreservationsystem.service;


import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.ReservationNotFound;
import com.dancodes.restaurantreservationsystem.model.Reservation;
import com.dancodes.restaurantreservationsystem.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    public ReservationServiceImpl(RestaurantService restaurantService, ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    // public Reservation bookRestaurant(ReservationRequest reservationRequest) {
    //     LOGGER.info("bookRestaurant() is invoked");
    //     Restaurant restaurant = validateRestaurant(reservationRequest.getRestaurantId());
    //     User user = userService.manageUser(reservationRequest);

    //     // Long restaurantId = reservationRequest.getRestaurantId();
    //     // Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
    //     // if (!(restaurantOptional.isPresent())) {
    //     //     throw new RestaurantNotFound("No restaurant found with id" + restaurantId);
    //     // }
    //     // LOGGER.info("restaurant found with id: {}", restaurantId);
    //     // Restaurant restaurant = restaurantOptional.get();
    //     // User user = new User();
    //     // user.setFirstName(reservationRequest.getUserFirstName());
    //     // user.setLastName(reservationRequest.getUserLastName());
    //     // user.setEmail(reservationRequest.getUserEmail());
    //     // user.setPhoneNumber(reservationRequest.getUserPhoneNumber());

    //     // restaurantRepository.save(restaurant);
    //     // LOGGER.info("Saved the restaurant", restaurant);
        
    //     // Reservation reservation = new Reservation();
    //     // reservation.setRestaurant(restaurant);
    //     // reservation.setUser(user);
    //     // reservation.setNumberOfPeople(reservationRequest.getNumberOfPeople());
    //     // reservation.setCheckedIn(false);

    //     // reservationRepository.save(reservation);
    //     // LOGGER.info("Saved the reservation", reservation);

    //     return reservation;
    // }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationNotFound("No reservation found with id " + reservationId));
    }

    @Override
    public Reservation createReservation(ReservationCreateRequest reservationRequest) {
        // validate all request initially
        LOGGER.info("Service: CreateReservation() invoked");
        validateRequest(reservationRequest);

        LOGGER.info("Request is validated, starting to make a reservation");
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationRequest.getReservationDate());
        reservation.setReservationTime(reservationRequest.getReservationTime());
        reservation.setCheckedIn(false);
        reservation.setNumberOfPeople(reservationRequest.getNumberOfPeople());

        // String userEmail = reservationRequest.getUserEmail();
        // User user = userService.getUserByEmail(userEmail);
        // if (user != null) {
        //     reservation.setUser(user);
        // } else {
        //     reservation.setUserEmail(userEmail);
        // }
    
        // Restaurant restaurant = restaurantService.getRestaurantById(reservationRequest.getRestaurantId());
        // reservation.setRestaurant(restaurant);

        return reservationRepository.save(reservation);
    }

    public void validateRequest(ReservationCreateRequest reservationRequest) {
        LOGGER.info("Service: validateRequest() invoked");
        if (validateReservation(reservationRequest.getNumberOfPeople(), reservationRequest.getReservationDate())) {
            LOGGER.info("Reservation is validated");
        }
        if (userService.validateUser(reservationRequest.getUserEmail(), reservationRequest.getUserPhoneNumber())) {
            LOGGER.info("User is validated");
        }
        if (restaurantService.validateRestaurant(reservationRequest.getRestaurantId(), reservationRequest.getNumberOfPeople(), reservationRequest.getReservationDate(), reservationRequest.getReservationTime())) {
            LOGGER.info("Restaurant is validated");
        }
        
    }

    // private boolean isWithinOperatingHours(LocalTime reservationTime) {
    //     // Assume the restaurant operates from 10 AM to 10 PM
    //     LocalTime openingTime = LocalTime.of(10, 0);
    //     LocalTime closingTime = LocalTime.of(22, 0);
    //     return !reservationTime.isBefore(openingTime) && !reservationTime.isAfter(closingTime);   
    // }

    private boolean validateReservation(int numberOfPeople, LocalDate reservationDate) {
        LOGGER.info("Service: validateReservation() invoked");
        // Check for a valid number of people (e.g., not zero, within restaurant capacity limits)
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("Number of people must be greater than zero");
        }

        // Ensure the reservation date is in the future
        if (!reservationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation date must be in the future");
        }

        // // Check if the reservation time is within the restaurant's operating hours
        // if (!isWithinOperatingHours(reservationTime)) {
        //     throw new IllegalArgumentException("Reservation time is outside of operating hours");
        // }

        return true;
    }

}
