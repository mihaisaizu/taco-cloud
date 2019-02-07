package com.tacos.repositoies;

import com.tacos.model.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();

	Ingredient findById(String id);

	Ingredient saveIngredient(Ingredient ingredient);
}
