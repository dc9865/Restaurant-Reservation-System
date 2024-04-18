// package com.dancodes.restaurantreservationsystem.util;

// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;
// import com.dancodes.restaurantreservationsystem.model.Reservation;

// public class ValueMapper {
    

//     public static ReservationRequest convertToDTO(Reservation reservation) {
//         ReservationRequest reservationRequest = new ReservationRequest();
//         reservationRequest.setNumberOfPeople(reservation.getNumberOfPeople());
//         reservationRequest.setRestaurantId(reservation.getRestaurant().getId());
//         reservationRequest.setUserEmail(reservation.getUser().getEmail());
//         reservationRequest.setUserFirstName(reservation.getUser().getFirstName());
//         reservationRequest.setUserLastName(reservation.getUser().getLastName());
//         reservationRequest.setUserPhoneNumber(reservation.getUser().getPhoneNumber());

//         return reservationRequest;
        
//     }

//     // public static Reservation convertToEntity(ReservationRequest reservationRequest) {
//     //     Reservation reservation = new Reservation();
//     //     reservation.setCheckedIn(false);
//     //     reservation.setDate(reservationRequest.getDate());
//     //     reservation.setTime(reservationRequest.getTime());
//     //     reservation.setRestaurantId(reservationRequest.getRestaurantId());
//     //     reservation.setNameonUserCard(reservationRequest.getNameOnUserCard());
//     //     reservation.setNumberOfPeople(reservationRequest.getNumberOfPeople());
//     //     reservation.setRestaurant(reservationRequest.getRestaurant());
//     //     reservation.setUser(reservationRequest.getUser());



//     //     return reservation;
//     // }
// }
