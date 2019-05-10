package org.reader.app.controller;

import javax.validation.Valid;

import org.reader.app.form.GuestbookForm;
import org.reader.app.model.LoginUser;
import org.reader.app.repository.GuestbookRepository;
import org.reader.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * *
 * the guestBook controller
 */
@Controller
public class GuestbookController {

	// A special header sent with each AJAX request
	private static final String IS_AJAX_HEADER = "X-Requested-With=XMLHttpRequest";

	@Autowired
	private final GuestbookRepository guestbook;
	
	@Autowired
	UserRepository loginUser;

	/**
	 * 
	 */
	public GuestbookController(GuestbookRepository guestbook) {

		Assert.notNull(guestbook, "Guestbook must not be null!");
		this.guestbook = guestbook;
	}


	@GetMapping(path = "/")
	public String index() {
		return "redirect:/guestbook";
	}

	@GetMapping(path = "/guestbook")
	public	String guestBook(Model model,
			@ModelAttribute(binding = false) GuestbookForm form) {
		
		
		model.addAttribute("entries", guestbook.findAll());
		model.addAttribute("form", form);

		return "guestbook";
	}
	
	@GetMapping(path = "/guestbook/guest")
	public	String guestBookguest(Model model,
			@ModelAttribute(binding = false) GuestbookForm form) {
		
		
		model.addAttribute("entries", guestbook.findAll());
		model.addAttribute("form", form);

		return "guest";
	}

	@RequestMapping(value="/login/{username}",method=RequestMethod.GET)
	public String loginUser(@PathVariable String username) {
	LoginUser user=	loginUser.findByUsername(username);
		if(user.getUsername().contentEquals("admin") || user.getUsername().contentEquals("guest1")) {
			return "/redirect:/guestbook";
		}else
		return "error";
	}

	@PostMapping(path = "/guestbook")
	public String addEntry(@Valid @ModelAttribute("form") GuestbookForm form, Errors errors, Model model) {

		if (errors.hasErrors()) {
			return "guestbook";
		}

		guestbook.save(form.toNewEntry());

		return "redirect:/guestbook";
	}


	@PostMapping(path = "/guestbook", headers = IS_AJAX_HEADER)
	public String addEntry(@Valid GuestbookForm form, Model model) {

		model.addAttribute("entry", guestbook.save(form.toNewEntry()));
		model.addAttribute("index", guestbook.count());

		return "guestbook :: entry";
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/guestbook/{id}")
	public String removeEntry(@PathVariable Long id) {

		guestbook.deleteById(id);

		return "redirect:/guestbook";
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/guestbook/{id}", headers = IS_AJAX_HEADER)
	public HttpEntity<?> removeEntryJS(@PathVariable Long id) {

		return guestbook.findById(id).map(e -> {

			guestbook.deleteById(e.getId());
			return ResponseEntity.ok().build();

		}).orElse(ResponseEntity.notFound().build());
	}
}
