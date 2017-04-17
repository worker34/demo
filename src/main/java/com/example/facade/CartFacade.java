package com.example.facade;

import com.example.entity.Journal;
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

    @Autowired
    private HttpSession httpSession;

//    return cart of the authenticated user
    public Cart getCart(){
        Cart cart;
        cart = (Cart) httpSession.getAttribute("cart");
        if (cart == null) cart = new Cart();
        httpSession.setAttribute("cart", cart);
        return cart;
    }

    public void add(Journal addJournal) {
        Cart cart;
        cart = (Cart) httpSession.getAttribute("cart");
        if (cart == null) cart = new Cart();
        cart.add(addJournal);
        httpSession.setAttribute("cart", cart);
    }
}
