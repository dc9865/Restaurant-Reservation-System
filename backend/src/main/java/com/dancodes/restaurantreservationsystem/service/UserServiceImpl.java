package com.dancodes.restaurantreservationsystem.service;

import java.util.List;

import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.dto.UserCreateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.UserNotFound;

// import com.dancodes.restaurantreservationsystem.dto.ReservationRequest;

import com.dancodes.restaurantreservationsystem.model.User;
import com.dancodes.restaurantreservationsystem.model.UserType;
import com.dancodes.restaurantreservationsystem.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final BCryptPasswordEncoder passwordEncoder;
    // private final PasswordEncoder passwordEncoder;

    // public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    //     this.userRepository = userRepository;
    //     this.passwordEncoder = passwordEncoder;
    // }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                    "user with id " + userId + " does not exist"
                ));
        return user;
    }

    // public User getUserByEmail(String email) {
    //     User user = userRepository.findByEmail(email)
    //             .orElseThrow(() -> new IllegalStateException(
    //                 "user with email " + email + " does not exist"
    //             ));
    //     return user;
    // }

    // public void addNewUser(User user) {
    //     Optional<User> userEmailOptional = userRepository
    //             .findByEmail(user.getEmail());
    //     if (userEmailOptional.isPresent()) {
    //         throw new IllegalStateException("email taken");
    //     }

    //     if (user.getEmail() == null) {
    //         throw new IllegalStateException("email invalid");
    //     }
    //     if (user.getPassword() == null) {
    //         throw new IllegalStateException("password invalid");
    //     }
    //     if (user.getFirstName() == null || user.getLastName() == null) {
    //         throw new IllegalStateException("name invalid");
    //     }

    //     // String encodedPassword = passwordEncoder.encode(user.getPassword());
    //     // user.setPassword(encodedPassword);
    //     userRepository.save(user);
    // }

    // public void deleteUser(Long userId) {
    //     boolean exists = userRepository.existsById(userId);
    //     if (!exists) {
    //         throw new IllegalStateException("user with id " + userId + " does not exist.");
    //     }
    //     userRepository.deleteById(userId);
    // }

    // @Transactional
    // public void updateUser(Long userId, String firstName, String lastName, String email,
    //                          String password, String address, String phoneNumber) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new IllegalStateException(
    //                 "user with id " + userId + " does not exist"
    //             ));
    //     if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
    //         user.setFirstName(firstName);
    //         System.out.println(firstName);
    //     }
    //     if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
    //         user.setLastName(lastName);
    //         System.out.println(lastName);
    //     }
    //     if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
    //         Optional<User> userOptional = userRepository
    //                 .findByEmail(email);
    //         if (userOptional.isPresent()) {
    //             throw new IllegalStateException("email taken");
                
    //         }
    //         user.setEmail(email);
    //         System.out.println(email);
    //     }
    //     // TODO : Password (Not enough checks, may need to employ ASCI comparison checks)
    //     if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
    //         user.setPassword(password);
    //         System.out.println(password);
    //     }
    //     if (address != null && address.length() > 0 && !Objects.equals(user.getAddress(), address)) {
    //         user.setAddress(address);
    //         System.out.println(address);
    //     }
    //     // TODO: phoenNumber (lenghth > 0 may not be enough)
    //     if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(user.getPhoneNumber(), phoneNumber)) {
    //         user.setPhoneNumber(phoneNumber);
    //         System.out.println(phoneNumber);
    //     }
        
    // }

    @Override
    public boolean validateUser(String email, String phoneNumber) {
        // Check for email format validity
        validateEmail(email);
        validatePhoneNumber(phoneNumber);

        return true;
    }

    private void validateEmail(String email) {
        // Check if email is empty
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        // Add a check if email is valid
        // if (userRepository.existsByEmail(email)) {
        //     throw new EmailNotValid("Email: " + email + " already exists");
        // }
        // if (!(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))) {
        //     throw new EmailNotValid("Invalid email sytntax");
        // }
    }

    private void validatePhoneNumber(String phoneNumber) {
        // Check if phone number is empty
        if (phoneNumber == null) {
            throw new IllegalArgumentException("phoneNumber cannot be null");
        }
        // Add a check if phone number is valid
        // if (userRepository.existsByPhoneNumber(phoneNumber)) {
        //     throw new PhoneNumberNotValid("Phone Number: " + phoneNumber + " already exists");
        // }
    }

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {
        LOGGER.info("createUser() is invoked");
        // Validate email, and phoneNumber
        String email = userCreateRequest.getUsername();
        String phoneNumber = userCreateRequest.getPhoneNumber();
        if (validateUser(email, phoneNumber)) {
            LOGGER.info("The user is validated");
        }

        User user = new User();
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());
        user.setUsername(userCreateRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(encodedPassword);
        user.setAddress(userCreateRequest.getAddress());
        user.setPhoneNumber(userCreateRequest.getPhoneNumber());
        user.setReservations(null);
        user.setUserType(UserType.REGULAR_USER);
        return userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) throws UserNotFound {
        LOGGER.info("Authenticating user with username: {}", username);
        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalStateException(
            "user with username, " + username + " does not exist"
        ));
        LOGGER.info("Found user: {}", user);
        if (passwordEncoder.matches(password, user.getPassword())) {
            LOGGER.info("Password matches for user: {}", username);
            return user;
        } else {
            LOGGER.error("Password does not match for user: {}", username);
            throw new UserNotFound(password + " is incorrect for user: " + username);
        }
    }
    

}
