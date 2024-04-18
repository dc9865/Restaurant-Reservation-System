package com.dancodes.restaurantreservationsystem.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.dto.ReservationCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.EmailNotValid;
// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFoundException;
import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.model.UserType;
import com.dancodes.restaurantreservationsystem.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    // private final PasswordEncoder passwordEncoder;

    // public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    //     this.userRepository = userRepository;
    //     this.passwordEncoder = passwordEncoder;
    // }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                    "user with id " + userId + " does not exist"
                ));
        return user;
    }

    public void addNewUser(User user) {
        Optional<User> userEmailOptional = userRepository
                .findUserByEmail(user.getEmail());
        if (userEmailOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        if (user.getEmail() == null) {
            throw new IllegalStateException("email invalid");
        }
        if (user.getPassword() == null) {
            throw new IllegalStateException("password invalid");
        }
        if (user.getFirstName() == null || user.getLastName() == null) {
            throw new IllegalStateException("name invalid");
        }

        // String encodedPassword = passwordEncoder.encode(user.getPassword());
        // user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String firstName, String lastName, String email,
                             String password, String address, String phoneNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                    "user with id " + userId + " does not exist"
                ));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
            System.out.println(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
            System.out.println(lastName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email taken");
                
            }
            user.setEmail(email);
            System.out.println(email);
        }
        // TODO : Password (Not enough checks, may need to employ ASCI comparison checks)
        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
            System.out.println(password);
        }
        if (address != null && address.length() > 0 && !Objects.equals(user.getAddress(), address)) {
            user.setAddress(address);
            System.out.println(address);
        }
        // TODO: phoenNumber (lenghth > 0 may not be enough)
        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(user.getPhoneNumber(), phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
            System.out.println(phoneNumber);
        }
        
    }

    @Override
    public boolean validateUser(String email) {
        // Check if the email is provided
        if (email == null) {
            throw new IllegalArgumentException("Email is empty");
        }

        // Check for email format validity
        validateEmail(email);
        LOGGER.info("user's email is validated");

        return true;

    }

    private void validateEmail(String email) {
        // Add a check if email is valid
        if (userRepository.existsByEmail(email)) {
            throw new EmailNotValid("Email {} already exists" + email);
        }
        if (!(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))) {
            throw new EmailNotValid("Invalid email sytntax");
        }
    }

    

}
