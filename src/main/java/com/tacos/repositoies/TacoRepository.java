package com.tacos.repositoies;

import org.springframework.data.repository.CrudRepository;

import com.tacos.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, String> {

}
