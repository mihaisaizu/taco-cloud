package com.tacos.repositoies;

import org.springframework.data.repository.CrudRepository;

import com.tacos.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
}
