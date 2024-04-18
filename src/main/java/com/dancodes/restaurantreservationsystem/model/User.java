package com.dancodes.restaurantreservationsystem.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User extends AbstractEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName; 

    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    // @JoinColumn(name="reservation_id")
    @OneToMany
    private List<Reservation> reservations;

    @Enumerated(EnumType.STRING) // By default, Hibernate maps enums using the 'EnumType.ORDINAL'. Safer to use 'EnumType.String' to avoid issues if the order of the num values changes.
    private UserType userType; //enum to indicate the user type

    // @ManyToMany
    // @JoinTable(
    //     name = "User_Role",
    //     joinColumns = @JoinColumn(name="userId"),
    //     inverseJoinColumns = @JoinColumn(name="roleId")
    // )
    
    // public String toString() {
    //     return "User{" + 
    //             "firstName='" + firstName + '\'' + 
    //             ", lastName='" + lastName + '\'' + 
    //             ", email='" + email + '\'' + 
    //             ", password='" + password + '\'' + 
    //             '}';
    // }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}