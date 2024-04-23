package com.dancodes.restaurantreservationsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.dto.RestaurantCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.RestaurantNotFound;
import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.service.RestaurantService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {
    
    private final RestaurantService restaurantService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantRestController.class);

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        LOGGER.info("getRestaurants() is invoked");
        try {
            List<Restaurant> restaurants = restaurantService.getRestaurants();
            return new ResponseEntity<>(restaurants, HttpStatus.FOUND);
        } catch (RestaurantNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
        LOGGER.info("getRestaurantById() is invoked for id: " + id);
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(id);
            return new ResponseEntity<>(restaurant, HttpStatus.FOUND);
        } catch (RestaurantNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        LOGGER.info("createRestaurant() is invoked for: " + restaurantCreateRequest.toString());
        try {
            Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateRequest);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
