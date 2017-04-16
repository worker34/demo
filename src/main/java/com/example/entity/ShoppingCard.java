package com.example.entity;

import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 4/8/2017.
 */

@Scope("session")
public class ShoppingCard {

    private List<Book> books = new ArrayList();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
