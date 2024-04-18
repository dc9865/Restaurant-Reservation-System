package com.dancodes.restaurantreservationsystem.service;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;
import com.dancodes.restaurantreservationsystem.model.User;

public interface UserService {

    public boolean validateUser(String email);
}
