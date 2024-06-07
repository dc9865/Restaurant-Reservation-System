package com.dancodes.restaurantreservationsystem.util;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.model.Reservation;

public class ReservationConverter implements Converter<ReservationCreateRequest, Reservation> {

    @Override
    public ReservationCreateRequest convertToDTO(Reservation reservation) {
        ReservationCreateRequest request = new ReservationCreateRequest();

        request.setRestaurantId(reservation.getRestaurant().getId());
        request.setUserFirstName(reservation.getUser().getFirstName());
        request.setUserLastName(reservation.getUser().getLastName());
        request.setUserEmail(reservation.getUser().getEmail());
        request.setUserPhoneNumber(reservation.getUser().getPhoneNumber());
        request.setNumberOfPeople(reservation.getNumberOfPeople());
        request.setCheckedIn(false);
        request.setReservationTime(reservation.getReservationTime());
        request.setReservationDate(reservation.getReservationDate());

        return request;
    }

    @Override
    public Reservation convertTOEntity(ReservationCreateRequest request) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setNumberOfPeople(request.getNumberOfPeople());
        reservation.setCheckedIn(request.isCheckedIn());

        return reservation;
    }
    
}
