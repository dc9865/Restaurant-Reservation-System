package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.ReservationNotFound;
import com.dancodes.restaurantreservationsystem.model.Reservation;
import com.dancodes.restaurantreservationsystem.service.ReservationService;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reservations")
public class ReservationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> findReservations() {
        LOGGER.info("getReservations() is invoked");
        try {
            List<Reservation> reservations = reservationService.getReservations();
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (ReservationNotFound e) {
            LOGGER.error("No reservations found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching reservations", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Reservation> findReservation(@PathVariable("id") Long id) {
        LOGGER.info("findReservation() is invoked for id: " + id);
        try {
            Reservation reservation = reservationService.getReservationById(id);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (ReservationNotFound e) {
            LOGGER.error("Reservation not found with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching the reservation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationCreateRequest reservationCreateRequest) {
        LOGGER.info("createReservation() is invoked for: {}", reservationCreateRequest.toString());
        try {
            Reservation reservation = reservationService.createReservation(reservationCreateRequest);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("An error occurred while creating the reservation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
