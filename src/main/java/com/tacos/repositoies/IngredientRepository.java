package com.tacos.repositoies;

import org.springframework.data.repository.CrudRepository;

import com.tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
