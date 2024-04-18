package com.dancodes.restaurantreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.MenuItem;

// @Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // boolean existsById(Long id);
}
