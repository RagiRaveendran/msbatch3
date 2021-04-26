package com.s1.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s1.ms.ordermanagement.h2.model.SL_ORDERS;
import com.s1.ms.ordermanagement.h2.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	public List<SL_ORDERS> getAllOrders(){
		List<SL_ORDERS> orders = new ArrayList<>();
		
		orderRepo.findAll().forEach(orders::add);
		return orders;
		
	}
	
	public Optional<SL_ORDERS> getOrderByID(int id){		
		return orderRepo.findById(id);		
	}
	
	public void deleteOrderByID(int id){		
		orderRepo.deleteById(id);		
	}
	
	public void saveOrder(int order_id, SL_ORDERS order) {
		orderRepo.save(order);
	}
	

}
