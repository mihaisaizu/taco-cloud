package com.tacos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	private Date createdAt;

	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min=2, message="You must choose at least 2 ingredient")
	private List<String> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
