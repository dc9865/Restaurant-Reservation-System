package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // @GetMapping("/search")
    // public List<Restaurant> getRestaurants() {
    //     return restaurantService.getRestaurants();
    // }
    
    // @PostMapping
    // public void registerNewRestaurant(@RequestBody Restaurant restaurant) {
    //     restaurantService.addNewRestaurant(restaurant);
    // }

    // @DeleteMapping(path="{restaurant_id}")
    // public void deleteRestaurant(@PathVariable("restaurant_id") Long restaurantId) {
    //     restaurantService.deleteRestaurant(restaurantId);
    // }

    // @PutMapping(path={"restaurantId"})
    // public void updateRestaurant(@PathVariable("restaurant_id") Long restaurantId,
    //                              @RequestParam(required=false) Long menuItemId,
    //                              @RequestParam(required=false) Long reservationId,
    //                              @RequestParam(required=false) String name,
    //                              @RequestParam(required=false) String email,
    //                              @RequestParam(required=false) String number,
    //                              @RequestParam(required=false) String address) {
    //     restaurantService.updateRestaurant(restaurantId, menuItemId, reservationId, name, email, number, address);
    // }
}
