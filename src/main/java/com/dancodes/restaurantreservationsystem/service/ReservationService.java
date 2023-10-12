package com.dancodes.restaurantreservationsystem.service;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.model.Reservation;
import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.repository.ReservationRepository;
import com.dancodes.restaurantreservationsystem.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public void addNewReservation(Reservation reservation) {
        // In the future, if there is any data that has unique constraints,
        // will have to make a case for it as if the data is already taken
        // by another restaurant, you cannot add the new restaurant.
        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Boolean id_exists = reservationRepository.existsById(id);
        if (!id_exists) {
            throw new IllegalStateException("reservation with id" + id + "does not exist.");
        }
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void updateReservation(Long reservationId, Date date, Time time, int numberOfPeople, Restaurant restaurant) {
        Reservation reservation = reservationRepository.findById(reservationId)
                            .orElseThrow(() -> new IllegalStateException(
                                "reservation with id " + reservationId + " does not exist"
                            ));
        boolean dateChanged = date != null && !Objects.equals(reservation.getDate(), date);
        boolean timeChanged = time != null && !Objects.equals(reservation.getTime(), time);
    
        if (dateChanged || timeChanged) {
            Date effectiveDate = dateChanged ? date : reservation.getDate();
            Time effectiveTime = timeChanged ? time : reservation.getTime();
    
            Optional<Reservation> overlappingReservation = reservationRepository.findByDateAndTime(effectiveDate, effectiveTime);
            if (overlappingReservation.isPresent()) {
                throw new IllegalStateException("There's already a reservation made at this time and date");
            }
    
            if (dateChanged) {
                reservation.setDate(date);
                System.out.println(date);
            }
            if (timeChanged) {
                reservation.setTime(time);
                System.out.println(time);
            }
        }
                        

        // if ((address != null) && (address.length() > 0) && !Objects.equals(restaurant.getAddress(), address)) {
        //     restaurant.setAddress(address);
        //     System.out.println(address);
        // }
    }
}
