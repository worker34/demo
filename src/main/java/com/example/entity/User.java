package com.example.entity;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Igor on 4/1/2017.
 */
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid first name")
    @NotNull(message = "First Name is required")
    @Size(min=4, max=30, message="First Name must be between {min} and {max} characters long")
    @Column(name = "f_name")
    private String firstName;

    @Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid last name")
    @NotNull(message = "Last Name is required")
    @Size(min=4, max=30, message="Last Name must be between {min} and {max} characters long")
    @Column(name = "l_name")
    private String lastName;

    @NotNull(message = "Address is required")
    @Size(min=4, max=30, message="Address must be between {min} and {max} characters long")
    @Column(name = "address")
    private String address;

    @Pattern(regexp="^[0-9]{9,14}$", message="Invalid phone.")
    @NotNull(message = "Phone is required")
    @Size(min=4, max=30, message="Phone must be between {min} and {max} characters long")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Username is required")
    @Size(min=4, max=30, message="Username must be between {min} and {max} characters long")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min=4, max=30, message="Password must be between {min} and {max} characters long")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Password Coonfirmation is required")
    private String confirmPassword;

    @Min(value = 0, message = "Cash Need to be great than 0")
    @Digits(integer = 5 /*precision*/, fraction = 4 /*scale*/, message = "Please check your Cash")
    private Float cash;

    private Cart cart;

    @OneToMany
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
