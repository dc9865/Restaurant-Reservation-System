package com.dancodes.restaurantreservationsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public void addNewRestaurant(Restaurant restaurant) {
        // In the future, if there is any data that has unique constraints,
        // will have to make a case for it as if the data is already taken
        // by another restaurant, you cannot add the new restaurant.
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        Boolean id_exists = restaurantRepository.existsById(id);
        if (!id_exists) {
            throw new IllegalStateException("restaurant with id" + id + "does not exist.");
        }
        restaurantRepository.deleteById(id);
    }

    @Transactional
    public void updateRestaurant(Long restaurantId, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                            .orElseThrow(() -> new IllegalStateException(
                                "restaurant with id " + restaurantId + " does not exist"
                            ));
        if ((name != null) && (name.length() > 0) && !Objects.equals(restaurant.getName(), name)) {
            restaurant.setName(name);
            System.out.println(name);
        }

        if ((address != null) && (address.length() > 0) && !Objects.equals(restaurant.getAddress(), address)) {
            restaurant.setAddress(address);
            System.out.println(address);
        }
    }
}
