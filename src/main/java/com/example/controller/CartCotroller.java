package com.example.controller;

import com.example.entity.Book;
import com.example.entity.Cart;
import com.example.entity.User;
import com.example.facade.CartFacade;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tzurc on 4/15/2017.
 */
@Controller
public class CartCotroller {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartFacade cartFacade;

    @Autowired
    Cart cart;

    @RequestMapping("/addToCard/{id}")
    @Secured({"ADMIN", "USER"})
    public String addToCard(@PathVariable("id")long id, Model model, HttpServletRequest request){
        System.out.println("From addToCard() Controller! bookID: " + id);
        Book addBook = bookRepository.findBookById(id);
        cart.add(addBook);
        model.addAttribute("book", addBook);
        return "view";
    }

    @RequestMapping("/viewCart")
    @Secured({"ADMIN", "USER"})
    public String cart(Model model){
        model.addAttribute("cart", cartFacade.getCart());
        return "cart";
    }
}
