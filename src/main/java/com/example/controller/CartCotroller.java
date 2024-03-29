package com.example.controller;

import com.example.entity.Journal;
import com.example.entity.User;
import com.example.facade.CartFacade;
import com.example.repository.JournalRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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



    @GetMapping("/add-to-card/{id}")
//    @PreAuthorize("hasAnyAuthority('CART_WRITE_PRIVILEGE')")
    @PreAuthorize("hasPermission('#id', 'CART', 'WRITE')")
    public String addToCard(@PathVariable("id")int id, Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        Journal addJournal = journalRepository.findJournalById(id);
        cartFacade.add(addJournal);
        model.addAttribute("journal", addJournal);
        return "redirect:/view-cart";
    }

    @GetMapping("/view-cart")
//    @PreAuthorize("hasAnyAuthority('CART_READ_PRIVILEGE')")
    @PreAuthorize("hasPermission('id', 'CArt', 'READ')")
    public String cart(Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        model.addAttribute("cart", cartFacade.getCart());
        model.addAttribute("totalPrice", cartFacade.getPrice());
        return "cart";
    }

    @GetMapping("cart-pay")
//    @PreAuthorize("hasAnyAuthority('CART_WRITE_PRIVILEGE')")
    @PreAuthorize("hasPermission('id', 'Cart', 'WRITE')")
    public String cartPay(Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        User user = userRepository.findUserByUsername(principal.getName());
        if(!(user.getCash() >= cartFacade.getPrice())){
            model.addAttribute("msg", "You not have suficient money");
            model.addAttribute("cart", cartFacade.getCart());
            model.addAttribute("totalPrice", cartFacade.getPrice());
        }else{
            user.setCash(user.getCash() - cartFacade.getPrice());
            userRepository.save(user);
            model.addAttribute("cart", cartFacade.getCart());
            model.addAttribute("msg", cartFacade.cartPay());
        }
        return "cart";
    }
}
