package com.tacos.repositoies;

import com.tacos.model.Order;

public interface OrderRepository {
	Order save(Order order);
}
