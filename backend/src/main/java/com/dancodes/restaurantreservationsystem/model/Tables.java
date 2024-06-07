// package com.dancodes.restaurantreservationsystem.model;

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "tables")
// public class Tables extends AbstractEntity {

//     @Column(name = "capacity")
//     private int capacity;

//     @Column(name = "status")
//     private TableStatus status;

//     @OneToMany(mappedBy="tables")
//     @JsonManagedReference
//     private List<Reservation> reservations;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "restaurant_id")
//     @JsonBackReference
//     private Restaurant restaurant;


//     public int getCapacity() {
//         return capacity;
//     }

//     public void setCapacity(int capacity) {
//         this.capacity = capacity;
//     }

//     public List<Reservation> getReservations() {
//         return reservations;
//     }

//     public void setReservations(List<Reservation> reservations) {
//         this.reservations = reservations;
//     }

//     public Restaurant getRestaurant() {
//         return restaurant;
//     }

//     public void setRestaurant(Restaurant restaurant) {
//         this.restaurant = restaurant;
//     }
    
//     public TableStatus getStatus() {
//         return status;
//     }

//     public void setStatus(TableStatus status) {
//         this.status = status;
//     }
// }

package com.dancodes.restaurantreservationsystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class Tables extends AbstractEntity {

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private TableStatus status;

    @OneToMany(mappedBy="tables")
    @JsonManagedReference("table-reservations")
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference("restaurant-tables")
    private Restaurant restaurant;


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
}