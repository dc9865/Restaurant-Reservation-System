package com.dancodes.restaurantreservationsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.exceptions.RestaurantNotFound;
import com.dancodes.restaurantreservationsystem.repository.MenuItemRepository;
import com.dancodes.restaurantreservationsystem.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public boolean validateRestaurant(Long id, int numberOfPeople, LocalDate reservationDate, LocalTime reservationTime) {
        
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFound("Restaurant with the id: {} does not exist" + id);
        }

         // Check for a valid number of people (e.g., not zero, within restaurant capacity limits)
         if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("Number of people must be greater than zero");
        }

        // Ensure the reservation date is in the future
        if (!reservationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation date must be in the future");
        }

        if (!reservationTime.isAfter(LocalTime.now())) {
            throw new IllegalArgumentException("Reservation time must be in the future");
        }

        return true;
    }

    // public List<Restaurant> getRestaurants() {
    //     return restaurantRepository.findAll();
    // }

    // public Restaurant getRestaurant(Long restaurantId) {
    //     Restaurant restaurant = restaurantRepository.findById(restaurantId)
    //                         .orElseThrow(() -> new IllegalStateException(
    //                             "restaurant with id " + restaurantId + " does not exist"
    //                         ));
    //     return restaurant;
    // }

    // // Admins can add new restaurants to the system
    // public void addNewRestaurant(Restaurant restaurant) {
    //     // In the future, if there is any data that has unique constraints,
    //     // will have to make a case for it as if the data is already taken
    //     // by another restaurant, you cannot add the new restaurant.
    //     restaurantRepository.save(restaurant);
    // }

    // public void deleteRestaurant(Long id) {
    //     Boolean id_exists = restaurantRepository.existsById(id);
    //     if (!id_exists) {
    //         throw new IllegalStateException("restaurant with id" + id + "does not exist.");
    //     }
    //     restaurantRepository.deleteById(id);
    // }

    // @Transactional
    // public void updateRestaurant(Long restaurantId, Long menuItemId, Long reservationId, 
    //                                 String name, String email, String phoneNumber, 
    //                                 String address) {
    //     Restaurant restaurant = restaurantRepository.findById(restaurantId)
    //                         .orElseThrow(() -> new IllegalStateException(
    //                             "restaurant with id " + restaurantId + " does not exist"
    //                         ));
    //     if ((name != null) && (name.length() > 0) && !Objects.equals(restaurant.getName(), name)) {
    //         restaurant.setName(name);
    //         System.out.println(name);
    //     }
    //     if ((email != null) && (email.length() > 0) && !Objects.equals(restaurant.getEmail(), email)) {
    //         restaurant.setEmail(email);
    //         System.out.println(email);
    //     }
    //     if ((phoneNumber != null) && (phoneNumber.length() > 0) && !Objects.equals(restaurant.getPhoneNumber(), phoneNumber)) {
    //         restaurant.setPhoneNumber(phoneNumber);
    //         System.out.println(phoneNumber);
    //     }
    //     if ((address != null) && (address.length() > 0) && !Objects.equals(restaurant.getAddress(), address)) {
    //         restaurant.setAddress(address);
    //         System.out.println(address);
    //     }
    //     MenuItem menuItem = menuItemRepository.findById(menuItemId)
    //                         .orElseThrow(() -> new IllegalStateException(
    //                             "menu item with id " + menuItemId + " does not exist"
    //                         ));
    // }
}