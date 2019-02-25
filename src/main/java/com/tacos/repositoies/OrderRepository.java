package com.tacos.repositoies;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.tacos.model.Order;
import com.tacos.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
