package com.s1.ms.ordermanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.s1.ms.ordermanagement.h2.model.SL_ORDERS;
import com.s1.ms.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<List<SL_ORDERS>> getOrders() {
		
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/orders/{order_id}")
	public Optional<SL_ORDERS> getOrderById(@PathVariable int order_id) {
		
		return orderService.getOrderByID(order_id);
	}
	
	@PostMapping("/orders/{order_id}")
	public void addOrder(@PathVariable int order_id, @RequestBody SL_ORDERS order) {
		
		orderService.saveOrder(order_id, order);
	}
	
	@DeleteMapping("/orders/{order_id}")
	public void deleteOrderById(@PathVariable int id) {
		
		orderService.deleteOrderByID(id);
	}
}
