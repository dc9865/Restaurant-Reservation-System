package com.dancodes.restaurantreservationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFound;
import com.dancodes.restaurantreservationsystem.model.Reservation;

import com.dancodes.restaurantreservationsystem.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("restaurant_id") Long restaurantId, ModelMap modelMap) {
        LOGGER.info("showCompleteReservation() invoked with the Restaurant Id: " + restaurantId);


        return "reservation/completeReservation";
    }

    @GetMapping("/bookReservation")
    public String showReservationForm(ModelMap modelMap) {
        modelMap.addAttribute("reservationCreateRequest", new ReservationCreateRequest());
        return "/reservation/bookReservation"; // This should match the name of your HTML file
    }

    @GetMapping("/userNotFound")
    public String userNotFound(ModelMap modelMap) {
        return "reservation/userNotFound"; // Return the view name for Thymeleaf to resolve
    }

    @PostMapping("/bookReservation")
    public String bookReservation(@ModelAttribute("reservationCreateRequest") ReservationCreateRequest reservationCreateRequest, ModelMap modelMap){
        try {
            LOGGER.info("bookReservation() invoked with the reservation: " + reservationCreateRequest.toString());
            Reservation reservation = reservationService.createReservation(reservationCreateRequest);
            modelMap.addAttribute("message", "Reservation successful!");
            modelMap.addAttribute("reservationId", reservation.getId());
            modelMap.addAttribute("restaurantName", reservationCreateRequest.getRestaurantName());
            modelMap.addAttribute("reservationDate", reservationCreateRequest.getReservationDate());
            modelMap.addAttribute("reservationTime", reservationCreateRequest.getReservationTime());
            modelMap.addAttribute("numberOfPeople", reservationCreateRequest.getNumberOfPeople());
            modelMap.addAttribute("userFirstName", reservationCreateRequest.getUserFirstName());
            modelMap.addAttribute("userLastName", reservationCreateRequest.getUserLastName());
            modelMap.addAttribute("userEmail", reservationCreateRequest.getUserEmail());
            modelMap.addAttribute("userPhoneNumber", reservationCreateRequest.getUserPhoneNumber());
            return "reservation/reservationSuccess"; // Reservation is success
        } catch (UserNotFound e) {
            modelMap.addAttribute("message", "User not found!");
            return "reservation/userNotFound";
        } catch (Exception e) {
            modelMap.addAttribute("message", "Error processing your reservation!");
            return "reservation/reservationError";
        }
    }
}
