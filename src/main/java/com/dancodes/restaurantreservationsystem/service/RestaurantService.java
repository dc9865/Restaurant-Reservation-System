package com.dancodes.restaurantreservationsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;


public interface RestaurantService {

    public boolean validateRestaurant(Long id, int numberOfPeople, LocalDate reservationDate, LocalTime reservationTime);
}
