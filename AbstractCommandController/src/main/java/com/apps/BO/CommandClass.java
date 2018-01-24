package com.apps.BO;

import java.io.Serializable;

public class CommandClass implements Serializable {
	protected String name;
	protected String description;
	protected String brand;
	protected float price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CommandClass [name=" + name + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ "]";
	}

}
