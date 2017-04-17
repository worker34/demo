package com.example.controller;

import com.example.entity.Journal;
import com.example.entity.User;
import com.example.repository.JournalRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Scope("session")
public class JournalController {
	
	@Autowired
	JournalRepository journalRepository;

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/")	
	public String showAll(Model model){
		model.addAttribute("journals", journalRepository.findAll());
		return "index";
	}


	@RequestMapping("/edit/{id}")
	@Secured({"ADMIN"})
	public String updateJournal(@PathVariable("id")int id, Model model){
		model.addAttribute("journal", journalRepository.findJournalById(id));
		return "edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@Secured({"ADMIN"})
	public String saveUpdate(@PathVariable long id, @Valid @ModelAttribute("journal") Journal journal, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("journal", journal);
			return "edit";
		}
		journalRepository.save(journal);
		model.addAttribute("journals", journalRepository.findAll());
		return "redirect:/";
	}


	@RequestMapping("/delete/{id}")
	@Secured({"ADMIN"})
	public String delete(@PathVariable("id")int id, Model model){
		journalRepository.delete(id);
		model.addAttribute("journals", journalRepository.findAll());
		return "redirect:/";
	}

	@RequestMapping("/view/{id}")
	@Secured({"ADMIN", "USER"})
	public String viewJournal(@PathVariable("id")int id, Model model){
		model.addAttribute("journal", journalRepository.findJournalById(id));
		return "view";
	}
}
