package com.dancodes.restaurantreservationsystem.exceptions;


public class EmailNotValid extends RuntimeException {
    public EmailNotValid(String exception) {
        super(exception);
    }
}