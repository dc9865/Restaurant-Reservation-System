package com.dancodes.restaurantreservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// import com.dancodes.restaurantreservationsystem.config.JwtProperties;

@SpringBootApplication
// @EnableConfigurationProperties(JwtProperties.class)
public class RestaurantReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReservationSystemApplication.class, args);

	}

}
