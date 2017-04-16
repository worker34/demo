package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Book;
import com.example.repository.BookRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/")	
	public String showAll(Model model){
		model.addAttribute("books", bookRepository.findAll());
		return "index";
	}


	@RequestMapping("/edit/{id}")
	@Secured({"ADMIN"})
	public String updateBook(@PathVariable("id")int id, Model model){
		model.addAttribute("book", bookRepository.findBookById(id));
		return "edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@Secured({"ADMIN"})
	public String saveUpdate(@PathVariable long id, @ModelAttribute("book") Book book, Model model){
		bookRepository.save(book);
		model.addAttribute("books", bookRepository.findAll());
		return "redirect:/";
	}


	@RequestMapping("/delete/{id}")
	@Secured({"ADMIN"})
	public String delete(@PathVariable("id")int id, Model model){
		bookRepository.delete(id);
		model.addAttribute("books", bookRepository.findAll());
		return "redirect:/";
	}

	@RequestMapping("/view/{id}")
	@Secured({"ADMIN", "USER"})
	public String viewBook(@PathVariable("id")int id, Model model){
		model.addAttribute("book", bookRepository.findBookById(id));

		return "view";
	}
}
