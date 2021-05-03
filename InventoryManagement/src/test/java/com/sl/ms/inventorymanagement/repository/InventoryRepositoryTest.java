package com.sl.ms.inventorymanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InventoryRepositoryTest {

	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Test
	void contextLoads() {
		assertThat(inventoryRepo).isNotNull();
	}
}
