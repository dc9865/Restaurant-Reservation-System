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

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy="restaurant")
    private List<MenuItem> menuItems;

    // Assuming the Reservation entity has a field named
    // "restaurant" that refers back to this Restaurant entity.
    @OneToMany(mappedBy="restaurant")
    private List<Reservation> reservations = new ArrayList<>();

    public Restaurant(String name, String address, List<MenuItem> menuItems, List<Reservation> reservations) {
        this.name = name;
        this.address = address;
        this.menuItems = menuItems;
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // public String toString() {
    //     return "Restaurant{" +
    //             "name='" + name + '\'' +
    //             ", address='" + address + '\'' +
    //             ", menu='" + menu + '\'' +
    //             ", reservations='" + reservations + '\'' +
    //             '}';
    // }
}
