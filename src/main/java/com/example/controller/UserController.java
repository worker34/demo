package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by tzurc on 6/18/2017.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/singup")
    public String singup(Model model){
        model.addAttribute("person", new User());
        return "singup";
    }

    @PostMapping("/singup")
    @PreAuthorize("hasAnyAuthority('WRITE_USER_PRIVILEGE')")
    public String singup(@Valid @ModelAttribute("person") User user, BindingResult bindingResult, Model model){
        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "confirmPassword.person","Please confirm password");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("person", user);
            return "singup";
        }
        user.setRoles(new String[]{"READ_JOURNAL_PRIVILEGE", "READ_CART_PRIVILEGE", "WRITE_CART_PRIVILEGE"});
        System.out.println(user.getCash());
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('READ_USER_PRIVILEGE')")
    @PostAuthorize("#id == principal.username")
    public String update(@PathVariable("id")int id, Model model, Principal principal){
        System.out.println("Update user: " + principal.getName());
        model.addAttribute("person", userRepository.findUserById(id));
        return "singup";
    }


}
