package com.dancodes.restaurantreservationsystem.service;

import java.util.List;

import com.dancodes.restaurantreservationsystem.dto.UserCreateRequest;
import com.dancodes.restaurantreservationsystem.model.User;

public interface UserService {

    public boolean validateUser(String email, String phoneNumber);

    public List<User> getUsers();

    // public User getUserByEmail(String email);

    public User getUserById(Long id);

    public User createUser(UserCreateRequest userCreateRequest);

    public User authenticate(String username, String password);

}
