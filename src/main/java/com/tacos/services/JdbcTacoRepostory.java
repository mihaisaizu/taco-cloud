
package com.tacos.services;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tacos.model.Taco;
import com.tacos.repositoies.TacosRepository;

@Repository
public class JdbcTacoRepostory implements TacosRepository {

	private JdbcTemplate jdbc;

	public JdbcTacoRepostory(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco taco) {
		Long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (String ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}

	private Long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
				"INSERT INTO Taco (name, createdAt) VALUES (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
		pscFactory.setReturnGeneratedKeys(true);

		PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
				Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	private void saveIngredientToTaco(String ingredientId, Long tacoId) {
		jdbc.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?, ?)", tacoId, ingredientId);
	}
}
