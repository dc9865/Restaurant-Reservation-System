package com.dancodes.restaurantreservationsystem.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.Tables;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Long> {
    Optional<Set<Tables>> findByRestaurantId(Long restaurantId);
    Optional<Tables> findById(Long tableId);
    Optional<Tables> findByIdAndRestaurantId(Long tableId, Long restaurantId);
}
