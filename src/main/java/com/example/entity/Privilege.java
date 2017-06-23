package com.example.entity;

/**
 * Created by iturcanu on 6/23/2017.
 */
public class Privilege {

    private int id;
    private String name;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
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
}
