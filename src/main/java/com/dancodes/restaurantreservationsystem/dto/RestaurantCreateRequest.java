package com.dancodes.restaurantreservationsystem.dto;

import java.util.Set;

import com.dancodes.restaurantreservationsystem.model.Tables;

public class RestaurantCreateRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Set<Tables> tables;

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

    public Set<Tables> getTables() {
        return tables;
    }

    public void setTables(Set<Tables> tables) {
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
