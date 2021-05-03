package com.sl.ms.inventorymanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductRepositoryTest {
	@Autowired
	private ProductRepository inventoryRepo;
	
	@Test
	void contextLoads() {
		assertThat(inventoryRepo).isNotNull();
	}

}
