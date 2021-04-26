package com.s1.ms.ordermanagement.h2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SL_ITEMS")
public class SL_ITEMS {

	@Id
	private int id;

	@Column(name = "ITEM_NAME")
	private String itemName;

	@Column(name = "QUANTITY")
	private String quantity;

	@Column(name = "PRICE")
	private String price;

	@Column(name = "AMOUNT")
	private String amount;

	public SL_ITEMS() {

	}

	public SL_ITEMS(int id, String itemName, String quantity, String price, String amount) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", price=" + price + ", amount="
				+ amount + "]";
	}

}
