package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.validation.UserConstraints;

@Data
@Entity // table name
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class User implements UserConstraints {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int id;
    @Column(name = "name") // column name
    private String name;
    @Column(name = "age") // column name
    private Integer age;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // mappedBy is the field in the UserDetails class that
                                                            // references the User class
    private UserDetails userDetails;

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails; // set the userDetails in the user
        userDetails.setUser(this); // set the user in the userDetails
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    // Add order to user (maintains bidirectional relationship)
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    // Remove order from user
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public User() { // JPA needs a default constructor
    }

    // Implement getters required by UserConstraints
    @Override
    public String getAddress() {
        return userDetails != null ? userDetails.getAddress() : null;
    }

    @Override
    public String getPhone() {
        return userDetails != null ? userDetails.getPhone() : null;
    }
}