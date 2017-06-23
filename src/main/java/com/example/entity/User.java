package com.example.entity;

import org.springframework.context.annotation.Scope;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by Igor on 4/1/2017.
 */
public class User {

    private int id;

    @Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid first name")
    @NotNull(message = "First Name is required")
    @Size(min=4, max=30, message="First Name must be between {min} and {max} characters long")
    private String firstName;

    @Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid last name")
    @NotNull(message = "Last Name is required")
    @Size(min=4, max=30, message="Last Name must be between {min} and {max} characters long")
    private String lastName;

    @NotNull(message = "Address is required")
    @Size(min=4, max=30, message="Address must be between {min} and {max} characters long")
    private String address;

    @Pattern(regexp="^[0-9]{9,14}$", message="Invalid phone.")
    @NotNull(message = "Phone is required")
    @Size(min=4, max=30, message="Phone must be between {min} and {max} characters long")
    private String phone;

    @NotNull(message = "Username is required")
    @Size(min=4, max=30, message="Username must be between {min} and {max} characters long")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min=4, max=30, message="Password must be between {min} and {max} characters long")
    private String password;

    @NotNull(message = "Password Coonfirmation is required")
    private String confirmPassword;

    @Min(value = 0, message = "Cash Need to be great than 0")
    @Digits(integer = 5 /*precision*/, fraction = 4 /*scale*/, message = "Please check your Cash")
    private Float cash;

    private Cart cart;

    private List<Role> roles;

    public int getId() {
        return id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
