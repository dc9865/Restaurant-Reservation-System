package com.dancodes.restaurantreservationsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.dancodes.restaurantreservationsystem.dto.RestaurantCreateRequest;
import com.dancodes.restaurantreservationsystem.model.Restaurant;


public interface RestaurantService {

    public boolean validateRestaurant(Long id, int numberOfPeople, LocalDate reservationDate, LocalTime reservationTime);

    public List<Restaurant> getRestaurants();

    public Restaurant getRestaurantById(Long id);

    public Restaurant getRestaurantByNameAndAddress(String name, String address);

    public Restaurant createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

}
