package com.s1.ms.ordermanagement.h2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SL_ORDERS {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "ORDER_NAME")
	private String name;

	@Column(name = "TOTAL_AMOUNT")
	private String totalAmount;
	
	@OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "id")
    private List<SL_ITEMS> items;

	public SL_ORDERS() {

	}

	public SL_ORDERS(int id, String name, String totalAmount) {
		super();
		this.id = id;
		this.name = name;
		this.totalAmount = totalAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public List<SL_ITEMS> getItems() {
		return items;
	}

	public void setItems(List<SL_ITEMS> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", totalAmount=" + totalAmount + "]";
	}

}
