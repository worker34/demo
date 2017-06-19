package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String singup(@Valid @ModelAttribute("person") User user, BindingResult bindingResult, Model model){
        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "confirmPassword.person","Please confirm password");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("person", user);
            return "singup";
        }
        user.setRoles(new String[]{"USER"});
        System.out.println(user.getCash());
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(Model model, Principal principal){
        System.out.println("Update user: " + principal.getName());
        model.addAttribute("person", userRepository.findUserByUsername(principal.getName()));
        return "singup";
    }
}
