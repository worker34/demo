package com.example.controller;

import com.example.entity.Privilege;
import com.example.entity.Role;
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
import java.util.Arrays;

/**
 * Created by tzurc on 6/18/2017.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/singup")
    public String singup(Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        model.addAttribute("person", new User());
        return "singup";
    }

    @PostMapping("/singup")
//    @PreAuthorize("hasAnyAuthority('USER_WRITE_PRIVILEGE')")
    @PreAuthorize("hasPermission('#id', 'User', 'WRITE')")
    public String singup(@Valid @ModelAttribute("person") User user, BindingResult bindingResult, Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "confirmPassword.person","Please confirm password");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("person", user);
            return "singup";
        }
        user.setRoles(Arrays.asList(new Role("ADMIN", Arrays.asList(new Privilege("READ_USER_PRIVILEGE"), new Privilege("READ_JOURNAL_PRIVILEGE"), new Privilege("READ_CART_PRIVILEGE"), new Privilege("WRITE_CART_PRIVILEGE")))));
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{username}")
//    @PreAuthorize("hasAnyAuthority('USER_READ_PRIVILEGE')")
    @PreAuthorize("hasPermission('#username', 'User', 'READ')")
//    @PostAuthorize("returnObject.username == principal.username")
    @PostAuthorize("(#username == principal.username) || hasAnyRole('ADMIN')")
    public String update(@PathVariable("username")String username, Model model, Principal principal){
        model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
        System.out.println("Update user: " + principal.getName());
        model.addAttribute("person", userRepository.findUserByUsername(username));
        return "singup";
    }
}
