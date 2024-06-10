package com.dancodes.restaurantreservationsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dancodes.restaurantreservationsystem.dto.LoginRequest;
import com.dancodes.restaurantreservationsystem.dto.UserCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFound;
import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.service.UserService;
import com.dancodes.restaurantreservationsystem.util.JwtResponse;
import com.dancodes.restaurantreservationsystem.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserCreateRequest userCreateRequest) {
        LOGGER.info("register() is invoked for: " + userCreateRequest.toString());
        try {
            User user = userService.createUser(userCreateRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("login() is invoked for username: " + loginRequest.getUsername());
        try {
            User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFound e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            LOGGER.error("Error during login", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
