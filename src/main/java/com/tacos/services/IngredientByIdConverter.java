package com.tacos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tacos.model.Ingredient;
import com.tacos.repositoies.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{

	private IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@Override
	public Ingredient convert(String source) {
		 Optional<Ingredient> optionalIngredient = ingredientRepository.findById(source);
			return optionalIngredient.isPresent() ? optionalIngredient.get() : null;
	}

}