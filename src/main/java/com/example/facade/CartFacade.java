package com.example.facade;

import com.example.entity.Book;
import com.example.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * Created by tzurc on 4/15/2017.
 */
@Component
public class CartFacade {

//    @Autowired
//    private HttpSession httpSession;

//    return cart of the authenticated user
    public Cart getCart(){
        Book book = new Book();
        book.setName("Head First Java!");
        book.setAuthor("Ciarls Bukovski!");
        Cart cart = new Cart();
        cart.add(book);
        return cart;
    }

}
