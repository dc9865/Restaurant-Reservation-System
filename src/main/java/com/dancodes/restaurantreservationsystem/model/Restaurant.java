package com.dancodes.restaurantreservationsystem.model;

import java.util.List;

public class Restaurant extends AbstractEntity {
    
    private String name;
    private String address;
    private List<MenuItem> menu;
    private Reservation reservations;

    public Restaurant(String name, String address, List<MenuItem> menu, Reservation reservations) {
        this.name = name;
        this.address = address;
        this.menu = menu;
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

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public Reservation getReservations() {
        return reservations;
    }

    public void setReservations(Reservation reservations) {
        this.reservations = reservations;
    }

    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", menu='" + menu + '\'' +
                ", reservations='" + reservations + '\'' +
                '}';
    }
}
