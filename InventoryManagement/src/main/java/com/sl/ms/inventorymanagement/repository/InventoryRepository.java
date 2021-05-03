package com.sl.ms.inventorymanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.sl.ms.inventorymanagement.model.Inventory;

@RestResource(path="inventory", rel="inventory")
public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}