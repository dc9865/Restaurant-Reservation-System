package com.dancodes.restaurantreservationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.Restaurant;

// @Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    // boolean existsById(Long id);
}
