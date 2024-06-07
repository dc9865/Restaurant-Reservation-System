// package com.dancodes.restaurantreservationsystem.model;

// import java.util.List;
// import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;

// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;

// @Entity
// // @Table(name="restaurant",  uniqueConstraints = @UniqueConstraint(columnNames={"address", "name"}))
// @Table(name="restaurant")
// public class Restaurant extends AbstractEntity{

//     @Column(name="name")
//     private String name;

//     @Column(name="email", unique=true)
//     private String email;

//     @Column(name="phone_number")
//     private String phoneNumber;

//     @Column(name="address")
//     private String address;

//     @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private Set<Tables> tables;

//     // Assuming the Reservation entity has a field named
//     // "restaurant" that refers back to this Restaurant entity.
//     // @OneToMany(mappedBy="restaurant")
//     @OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<Reservation> reservations;

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setPhoneNumber(String phoneNumber) {
//         this.phoneNumber = phoneNumber;
//     }

//     public String getPhoneNumber() {
//         return phoneNumber;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }
    
//     public Set<Tables> getTables() {
//         return tables;
//     }

//     public void setTables(Set<Tables> tables) {
//         this.tables = tables;
//     }

//     public List<Reservation> getReservations() {
//         return reservations;
//     }

//     public void setReservations(List<Reservation> reservations) {
//         this.reservations = reservations;
//     }

//     // public String toString() {
//     //     return "Restaurant{" +
//     //             "name='" + name + '\'' +
//     //             ", address='" + address + '\'' +
//     //             ", menu='" + menu + '\'' +
//     //             ", reservations='" + reservations + '\'' +
//     //             '}';
//     // }
// }

package com.dancodes.restaurantreservationsystem.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant extends AbstractEntity{

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("restaurant-tables")
    private Set<Tables> tables;

    // Assuming the Reservation entity has a field named
    // "restaurant" that refers back to this Restaurant entity.
    // @OneToMany(mappedBy="restaurant")
    @OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("restaurant-reservations")
    private List<Reservation> reservations;

    // public Long getId() {
    //     return this.id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
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