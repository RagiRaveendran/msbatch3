package com.s1.ms.ordermanagement.h2.repository;

import org.springframework.data.repository.CrudRepository;

import com.s1.ms.ordermanagement.h2.model.SL_ORDERS;

public interface OrderRepository extends CrudRepository<SL_ORDERS, Integer>{

	SL_ORDERS findOrderByid(int orderId);
	
	
}
