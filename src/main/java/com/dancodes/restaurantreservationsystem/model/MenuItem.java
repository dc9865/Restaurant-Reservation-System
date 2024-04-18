package com.dancodes.restaurantreservationsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
// @Table(name = "menuItem")
public class MenuItem extends AbstractEntity{

    private String name;
    private String description;
    private double price;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name="restaurant_id")
    @ManyToOne
    private Restaurant restaurant;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
