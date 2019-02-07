package com.tacos.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tacos.model.Ingredient;
import com.tacos.repositoies.IngredientRepository;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;

	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowIngredient);
	}

	@Override
	public Ingredient findById(String id) {
		return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id = ?", this::mapRowIngredient, id);

		/*
		 * Old fashioned findById return jdbc.queryForObject(
		 * "select id, name, type from Ingredient where id=?", new
		 * RowMapper<Ingredient>() { public Ingredient mapRow(ResultSet rs, int rowNum)
		 * throws SQLException { return new Ingredient( rs.getString("id"),
		 * rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type"))); }; },
		 * id);
		 */

	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		jdbc.update("INSERT INTO Ingredient(id, name, type) VALUES (?, ?, ?)", ingredient.getId(), ingredient.getName(),
				ingredient.getType().toString());
		return ingredient;
	}

	private Ingredient mapRowIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

}
