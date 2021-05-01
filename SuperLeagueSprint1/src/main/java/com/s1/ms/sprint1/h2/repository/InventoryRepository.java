package com.s1.ms.sprint1.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s1.ms.sprint1.h2.model.INVENTORY;

public interface InventoryRepository extends JpaRepository<INVENTORY, Integer>{

	INVENTORY findOrderByid(int orderId);
	
	
}
