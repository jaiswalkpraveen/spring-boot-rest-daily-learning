package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@lombok.Data
@Entity //table name
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int id;
    @Column(name = "name") // column name
    private String name;
    @Column(name = "age") // column name
    private int age;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // mappedBy is the field in the UserDetails class that references the User class
    private UserDetails userDetails;

    public User() { // JPA needs a default constructor
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails; // set the userDetails in the user
        userDetails.setUser(this); // set the user in the userDetails
    }   // this is the bi-directional mapping (UserDetails -> User and User -> UserDetails)

}
