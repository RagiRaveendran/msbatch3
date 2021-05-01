package com.s1.ms.sprint1.h2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
public class INVENTORY {

	@Id
	private int id;

	@Column(name = "Name")
	private String itemName;

	@Column(name = "Price")
	private Double price;

	@Column(name = "Quantity")
	private Double quantity;
	
	public INVENTORY(int id, String itemName, Double price, Double quantity) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public INVENTORY() {
		super();
	}

	@Override
	public String toString() {
		return "INVENTORY [id=" + id + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity + "]";
	}

	

}
