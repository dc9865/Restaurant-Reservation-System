package com.dancodes.restaurantreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dancodes.restaurantreservationsystem.model.Restaurant;

// @Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    boolean existsById(Long id);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAddress(String address);
    boolean existsByEmail(String email);
}
