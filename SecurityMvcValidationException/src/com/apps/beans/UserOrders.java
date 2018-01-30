package com.apps.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ORDERS",uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class UserOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERIC_SEQ")
	@SequenceGenerator(name = "GENERIC_SEQ", sequenceName = "GENERIC_SEQ", allocationSize = 1, initialValue = 1)
	private int Id;
	@Column(name = "AMOUNT")
	private float amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Column(name = "ORDER_NUM")
	private String number;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "QUANTITY")
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
