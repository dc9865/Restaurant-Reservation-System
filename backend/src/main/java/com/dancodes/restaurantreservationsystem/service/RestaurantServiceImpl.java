package com.dancodes.restaurantreservationsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.dto.RestaurantCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.RestaurantNotFound;
import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.repository.RestaurantRepository;


@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private final RestaurantRepository restaurantRepository;
    private static final String PHONE_NUMBER_PATTERN = "^\\+?([0-9]{1,3})?[-.\\s]?([(]?[0-9]{1,3}[)]?)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{3,4}[-.\\s]?[0-9]{3,4}$";

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
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

        if (reservationDate.isEqual(LocalDate.now()) && !reservationTime.isAfter(LocalTime.now())) {
            throw new IllegalArgumentException("Reservation time must be in the future");
        }

        return true;
    }
    private boolean validateRestaurant(String email, String phoneNumber, String address) {
        if (restaurantRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Restaurant with the email: " + email + "already exists");
        }
        // if (!isValidPhoneNumber(phoneNumber)) {
        //     throw new IllegalArgumentException("Invalid phone number syntax");
        // }
        if (restaurantRepository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Restuarant with the phone number: " + phoneNumber + "ralready exists");
        }
        if (restaurantRepository.existsByAddress(address)) {
            throw new IllegalArgumentException("Restaurant with the address: " + address + "arleady exists" );
        }
        return true;
    } 

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                            .orElseThrow(() -> new IllegalStateException(
                                "restaurant with id: " + restaurantId + " does not exist"
                            ));
        return restaurant;
    }

    @Override
    public Restaurant getRestaurantByNameAndAddress(String name, String address) {
        Restaurant restaurant = restaurantRepository.findByNameAndAddress(name, address)
                            .orElseThrow(() -> new IllegalStateException(
                                "restaurant with name: " + name + " and address: " + address + "does not exist"
                            ));
        return restaurant;
    }

    @Override
    // Admins can add new restaurants to the system
    public Restaurant createRestaurant(RestaurantCreateRequest request) {
        // Validate id, email, address, and phoneNumber only
        String email = request.getEmail();
        String address = request.getAddress();
        String phoneNumber = request.getPhoneNumber();
        if (validateRestaurant(email, address, phoneNumber)) {
            LOGGER.info("The restaurant is validated");
        }

        // Create a new restaurant and update information according to the data from DTO.
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setEmail(email);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setName(request.getName());
        restaurant.setTables(request.getTables());

        return restaurantRepository.save(restaurant);
    }

    // Admins can delete restaurants from the system
    public void deleteRestaurant(Long id) {
        Boolean id_exists = restaurantRepository.existsById(id);
        if (!id_exists) {
            throw new IllegalStateException("restaurant with id" + id + "does not exist.");
        }
        restaurantRepository.deleteById(id);
    }



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