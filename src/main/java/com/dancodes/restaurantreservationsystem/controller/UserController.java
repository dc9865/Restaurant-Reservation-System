package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/search")
    // public List<User> getUsers() {
    //     return userService.getUsers();
    // }

    // @PostMapping("/add")
    // public void registerNewUser(@Valid @RequestBody User user) {
    //     userService.addNewUser(user);
    // } 

    // @DeleteMapping(path = "{userId}")
    // public void deleteUser(@PathVariable("userId") Long userId) {
    //     userService.deleteUser(userId);
    // }

    // @PutMapping(path = "{userId}")
    // public void updateUser(
    //     @PathVariable("userId") Long userId,
    //     @RequestParam(required=false) String firstName,
    //     @RequestParam(required=false) String lastName,
    //     @RequestParam(required=false) String email,
    //     @RequestParam(required=false) String password,
    //     @RequestParam(required=false) String address,
    //     @RequestParam(required=false) String number
    //     ) {
    //     userService.updateUser(userId, firstName, lastName, email, password, address, number);
    // } 
}
