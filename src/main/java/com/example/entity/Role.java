package com.example.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by iturcanu on 6/23/2017.
 */
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinTable(name = "role_privilege",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;
}
