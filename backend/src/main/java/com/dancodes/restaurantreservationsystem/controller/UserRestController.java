package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.dto.UserCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFound;
import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.service.UserService;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserRestController {

    // private final ReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
    private final UserService userService;
    
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> findUsers() {
        LOGGER.info("getUsers() is invoked");
        try {
            List<User> reservations = userService.getUsers();
            return new ResponseEntity<>(reservations, HttpStatus.OK); 
        } catch (UserNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        LOGGER.info("findUserById() is invoked for id: " + id);
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<User> findUserByNameAndPassword(@PathVariable("id") Long id, ) {
    //     LOGGER.info("findUserById() is invoked for id: " + id);
    //     try {
    //         User user = userService.getUserById(id);
    //         return new ResponseEntity<>(user, HttpStatus.OK);
    //     } catch (UserNotFound e) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}