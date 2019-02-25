package com.tacos.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tacos.model.Ingredient;
import com.tacos.model.Order;
import com.tacos.model.Taco;
import com.tacos.model.User;
import com.tacos.model.Ingredient.Type;
import com.tacos.repositoies.IngredientRepository;
import com.tacos.repositoies.TacoRepository;
import com.tacos.repositoies.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;
	private final TacoRepository designRepository;
	private UserRepository userRepository;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepository) {
		this.ingredientRepository = ingredientRepo;
		this.designRepository = tacoRepo;
		this.userRepository = userRepository;
	}
	
	@ModelAttribute(name="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="design")
	public Taco taco() {
		return new Taco();
	}
		
	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		model.addAttribute("user", user);
		return "design";
	}
    
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		Taco saved = designRepository.save(taco);
		order.addDesign(saved);
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				.filter(ingredient -> ingredient.getType().equals(type))
				.collect(Collectors.toList());
	}
}