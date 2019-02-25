package com.tacos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tacos.model.RegistrationForm;
import com.tacos.repositoies.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String registerForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String processRegistration(RegistrationForm registrationForm, Errors errors) {
		if (errors.hasErrors()) {
			return "registration";
		}
		userRepository.save(registrationForm.toUser(passwordEncoder));
		return "redirect:/login";
	}
}
