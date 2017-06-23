package com.example.entity;

import java.util.List;

/**
 * Created by iturcanu on 6/23/2017.
 */
public class Role {

    private int id;
    private String name;

    public Role() {
    }

    public Role(String name, List<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    private List<Privilege> privileges;


}
