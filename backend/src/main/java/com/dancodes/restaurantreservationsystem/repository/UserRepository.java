package com.dancodes.restaurantreservationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dancodes.restaurantreservationsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // boolean existsById(Long id);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);
    

}
