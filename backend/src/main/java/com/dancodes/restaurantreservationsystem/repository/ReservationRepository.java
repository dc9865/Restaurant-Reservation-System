package com.dancodes.restaurantreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Optional<Reservation> findByDateAndTime(Date date, Time time);
    // boolean existsById(Long id);
}
