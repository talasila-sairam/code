package com.apps.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class CustomerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERIC_SEQ")
	@SequenceGenerator(name = "GENERIC_SEQ", sequenceName = "GENERIC_SEQ", allocationSize = 1, initialValue = 1)
	private int customerid;
	
	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	@Column(name="CUSTOMERNAME")
	private String customerName;
	
	@Column(name="CUSTOMERADDR")
	private String customerAddr;
	
	@Column(name="CUSTOMERPHONE")
	private String customerPhone;
	
	@Column(name="username")
	private String username;

	@Column(name="ADDR_TYPE")
	private String addressType;
	
	@Column(name="CUSTOMER_MAIL")
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
		public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

		private String code;
		private float cost;
		public float getCost() {
			return cost;
		}

		public void setCost(float cost) {
			this.cost = cost;
		}
		
}
