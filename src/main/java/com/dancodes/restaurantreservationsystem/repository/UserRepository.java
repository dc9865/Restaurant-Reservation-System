package com.dancodes.restaurantreservationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findUserByEmail(String email);
    // boolean existsById(Long id);

    boolean existsByEmail(String email);
}
