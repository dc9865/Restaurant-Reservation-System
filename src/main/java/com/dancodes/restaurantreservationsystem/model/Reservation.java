package com.dancodes.restaurantreservationsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="reservation", uniqueConstraints = @UniqueConstraint(columnNames={"reservationDate", "reservationTime", "restaurant_id"}))
public class Reservation extends AbstractEntity{

    @Column(name="numberOfPeople")
    private int numberOfPeople;

    @Column(name="CheckedIn")
    private boolean checkedIn;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String userEmail;

    private LocalTime reservationTime;

    private LocalDate reservationDate;

    @ManyToOne
    private Tables tables;

    // This specifies the column in the Reservation table (the 'many' side)
    // that will be used as the foreign key pointing to the primary key of the
    // Restaurant table (the 'one' side). The column name in the Reservation
    // table would be restaurant_id.
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name="restaurant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id") // Ensures this is the only reference to "restaurant_id"
    private Restaurant restaurant;

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }

        public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
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
