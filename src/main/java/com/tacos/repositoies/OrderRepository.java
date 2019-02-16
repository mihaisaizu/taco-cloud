package com.tacos.repositoies;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.tacos.model.Order;
import com.tacos.model.User;

public interface OrderRepository extends CrudRepository<Order, String> {
	
	/*
	 * List<Order> findByZip(String zip);
	 * 
	 * List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	 * 
	 * List<Order> findByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date
	 * startDate, Date endDate);
	 */

}
