package com.dancodes.restaurantreservationsystem.model;

import java.sql.Date;
import java.sql.Time;

public class Reservation extends AbstractEntity{

    private Date date;
    private Time time;
    private int numberOfPeople;
    private User regularUser;
    private Restaurant restaurant;
    
    public Reservation(Date date, Time time, int numberOfPeople, User regularUser, Restaurant restaurant) {
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.regularUser = regularUser;
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public User getUser() {
        return this.regularUser;
    }

    public void setUser(User regularUser) {
        this.regularUser = regularUser;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String toString() {
        return "Reservation{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", number of people='" + numberOfPeople + '\'' +
                ", regular user ='" + regularUser + '\'' +
                ", restaurant ='" + restaurant + '\'' +
                '}';
    }


}
