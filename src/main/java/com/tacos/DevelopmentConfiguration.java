package com.tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tacos.model.Ingredient;
import com.tacos.model.User;
import com.tacos.repositoies.IngredientRepository;
import com.tacos.repositoies.UserRepository;

@Configuration
@Profile("!prod")
public class DevelopmentConfiguration {
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, 
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
                ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
                ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
                ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
                ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
                ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
                
                userRepository.save(new User("test", passwordEncoder.encode("test"), "Matei Saizu", "123 Street Craiova", "Craiova", "Dj", "32323", "123-123-123-1234"));
			}
		};
	}
}
