package com.example.controller;

import com.example.entity.Cart;
import com.example.entity.Journal;
import com.example.entity.User;
import com.example.facade.CartFacade;
import com.example.repository.JournalRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tzurc on 4/15/2017.
 */
@Controller
public class CartCotroller {

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartFacade cartFacade;



    @RequestMapping("/addToCard/{id}")
    @Secured({"ADMIN", "USER"})
    public String addToCard(@PathVariable("id")long id, Model model){
        Journal addJournal = journalRepository.findJournalById(id);
        cartFacade.add(addJournal);
        model.addAttribute("journal", addJournal);
        return "redirect:/viewCart";
    }

    @RequestMapping("/viewCart")
    @Secured({"ADMIN", "USER"})
    public String cart(Model model){
        model.addAttribute("cart", cartFacade.getCart());
        return "cart";
    }
}
