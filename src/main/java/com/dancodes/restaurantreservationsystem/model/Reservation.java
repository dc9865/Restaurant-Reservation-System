package com.dancodes.restaurantreservationsystem.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Reservation", uniqueConstraints = @UniqueConstraint(columnNames={"date", "time"}))
public class Reservation extends AbstractEntity{

    @Column(name="date")
    private Date date;

    @Column(name="time")
    private Time time;

    @Column(name="numberOfPeople")
    private int numberOfPeople;

    @OneToMany(mappedBy="reservation")
    private List<User> users = new ArrayList<>();

    // This specifies the column in the Reservation table (the 'many' side)
    // that will be used as the foreign key pointing to the primary key of the
    // Restaurant table (the 'one' side). The column name in the Reservation
    // table would be restaurant_id.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    
    public Reservation(Date date, Time time, int numberOfPeople, Restaurant restaurant) {
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // public String toString() {
    //     return "Reservation{" +
    //             "date='" + date + '\'' +
    //             ", time='" + time + '\'' +
    //             ", number of people='" + numberOfPeople + '\'' +
    //             ", regular user ='" + users + '\'' +
    //             ", restaurant ='" + restaurant + '\'' +
    //             '}';
    // }


}
