package com.example.controller;

import com.example.entity.Journal;
import com.example.entity.SearchObject;
import com.example.repository.JournalRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = {"/**", "/journals"})
public class JournalController {

	@Autowired
	JournalRepository journalRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private HttpSession httpSession;


	@GetMapping("/")
//	@PreAuthorize("hasAnyAuthority('JOURNAL_READ_PRIVILEGE')")
	@PreAuthorize("hasPermission('#id', 'Journal', 'READ')")
	public String showAll(Model model, Principal principal){
//		httpSession.setAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		model.addAttribute("journals", journalRepository.findAll());
		model.addAttribute("search", new SearchObject());
		return "index";
	}

	@GetMapping("/{id}")
//	@PreAuthorize("hasAnyAuthority('JOURNAL_WRITE_PRIVILEGE')")
	@PreAuthorize("hasPermission('#id', 'Journal', 'WRITE')")
	public String updateJournal(@PathVariable("id")int id, Model model, Principal principal){
		model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		model.addAttribute("journal", journalRepository.findJournalById(id));
		return "edit";
	}

	@PutMapping(value = "/{id}")
//	@PreAuthorize("hasAnyAuthority('JOURNAL_WRITE_PRIVILEGE')")
	@PreAuthorize("hasPermission('#id', 'Journal', 'WRITE')")
	public String saveUpdate(@PathVariable long id, @Valid @ModelAttribute("journal") Journal journal, BindingResult bindingResult, Model model, Principal principal){
		if(bindingResult.hasErrors()){
			model.addAttribute("journal", journal);
			return "edit";
		}
		journalRepository.save(journal);
		model.addAttribute("journals", journalRepository.findAll());
		return "redirect:/";
	}


	@GetMapping("/delete/{id}")
//	@PreAuthorize("hasAnyAuthority('JOURNAL_DELETE_PRIVILEGE')")
	@PreAuthorize("hasPermission('#id', 'Journal', 'DELETE')")
	public String delete(@PathVariable("id")int id, Model model, Principal principal){
		model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		journalRepository.delete(id);
		model.addAttribute("journals", journalRepository.findAll());
		return "redirect:/";
	}

	@GetMapping("/view/{id}")
//	@PreAuthorize("hasAnyAuthority('JOURNAL_READ_PRIVILEGE')")
	@PreAuthorize("hasPermission('#id', 'Journal', 'read')")
	public String viewJournal(@PathVariable("id")int id, Model model, Principal principal){
		model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		model.addAttribute("journal", journalRepository.findJournalById(id));
		return "view";
	}

	@PostMapping("/search")
	public String search(@ModelAttribute("search") SearchObject searchObject, Model model, Principal principal){
		model.addAttribute("simpleUser", userRepository.findUserByUsername(principal.getName()));
		model.addAttribute("journals", journalRepository.findByCriteria(searchObject));
		model.addAttribute("search", searchObject);
		return "index";
	}
}
