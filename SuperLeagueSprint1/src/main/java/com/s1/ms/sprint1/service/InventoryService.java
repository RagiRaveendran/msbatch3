package com.s1.ms.sprint1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s1.ms.sprint1.h2.model.INVENTORY;
import com.s1.ms.sprint1.h2.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	InventoryRepository inventoryRepo;
	
	public void saveInventory(List<INVENTORY> inventoryList) {
		inventoryRepo.saveAll(inventoryList);
	}
	
	public List<INVENTORY> findAllInventory() {
		return inventoryRepo.findAll();
	}
	

}
