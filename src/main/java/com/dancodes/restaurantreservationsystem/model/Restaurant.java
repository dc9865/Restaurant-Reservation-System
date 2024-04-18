package com.dancodes.restaurantreservationsystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Restaurant")
public class Restaurant extends AbstractEntity {
    
    @Column(name="name")
    private String name;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy="restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy="restaurant")
    private List<Tables> tables;

    // Assuming the Reservation entity has a field named
    // "restaurant" that refers back to this Restaurant entity.
    // @OneToMany(mappedBy="restaurant")
    @OneToMany(mappedBy="restaurant")
    private List<Reservation> reservations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Tables> getTables() {
        return tables;
    }

    public void setTables(List<Tables> tables) {
        this.tables = tables;
    }

    // public String toString() {
    //     return "Restaurant{" +
    //             "name='" + name + '\'' +
    //             ", address='" + address + '\'' +
    //             ", menu='" + menu + '\'' +
    //             ", reservations='" + reservations + '\'' +
    //             '}';
    // }
}
