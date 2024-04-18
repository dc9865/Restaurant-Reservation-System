package com.dancodes.restaurantreservationsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFoundException;
import com.dancodes.restaurantreservationsystem.model.Reservation;
import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    // private final ReservationService reservationService;

    // public ReservationController(ReservationService reservationService) {
    //     this.reservationService = reservationService;
    // }

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("restaurant_id") Long restaurantId, ModelMap modelMap) {
        LOGGER.info("showCompleteReservation() invoked with the Restaurant Id: " + restaurantId);


        return "reservation/completeReservation";
    }

    @GetMapping("/userNotFound")
    public String userNotFound(ModelMap modelMap) {
        return "reservation/userNotFound"; // Return the view name for Thymeleaf to resolve
    }

    // @PostMapping("/bookReservation")
    // public String bookReservation(ReservationCreateRequest reservationRequest, ModelMap modelMap){
    //     try {
    //         LOGGER.info("completeReservation() invoked with the reservation: " + reservationRequest.toString());
    //         Reservation reservation = reservationService.createReservation(reservationRequest);
    //         // modelMap.addAttribute("message", "Reservation successful!");
    //         // modelMap.addAttribute("reservationId", reservation.getId());
    //         // modelMap.addAttribute("restaurantName", reservation.getRestaurant().getName());
    //         // modelMap.addAttribute("reservationDate", reservation.getReservationDate());
    //         return "reservation/reservationSuccess"; // Reservation is success
    //     } catch (UserNotFoundException e) {
    //         return "reservation/userNotFound"; // Redirect to a user decision page
    //     } catch (Exception e) {
    //         return "reservationError"; // Redirect to an error page
    //     }
    // }
}
