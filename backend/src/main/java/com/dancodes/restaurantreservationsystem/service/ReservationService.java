package com.dancodes.restaurantreservationsystem.service;

import java.util.List;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.model.Reservation;

public interface ReservationService {

    public List<Reservation> getReservations();
    public Reservation getReservationById(Long id);
    public Reservation createReservation(ReservationCreateRequest reservationCreateRequest);
}
