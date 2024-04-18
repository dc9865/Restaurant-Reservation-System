package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.ReservationNotFound;
import com.dancodes.restaurantreservationsystem.model.Reservation;
import com.dancodes.restaurantreservationsystem.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

    // private final ReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
    private final ReservationService reservationService;
    
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/search")
    public List<Reservation> getReservations() {
        LOGGER.info("getReservations() is invoked");
        return reservationService.getReservations();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Reservation> findReservation(@PathVariable("id") Long id) {
        try {
            LOGGER.info("findReservation() is invoked for id: " + id);
            Reservation reservation = reservationService.getReservationById(id);
            return new ResponseEntity<>(reservation, HttpStatus.ACCEPTED);
        } catch (ReservationNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationCreateRequest reservationCreateRequest) {
        try {
            LOGGER.info("createReservation() is invoked for: " + reservationCreateRequest);
            Reservation reservation = reservationService.createReservation(reservationCreateRequest);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (ReservationNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
