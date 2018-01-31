package com.apps.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="company")
public class Company {
	protected String cmpyName;
	protected String cmpyLocation;
	protected double revenue;
	public String getCmpyName() {
		return cmpyName;
	}
	public void setCmpyName(String cmpyName) {
		this.cmpyName = cmpyName;
	}
	public String getCmpyLocation() {
		return cmpyLocation;
	}
	public void setCmpyLocation(String cmpyLocation) {
		this.cmpyLocation = cmpyLocation;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	@Override
	public String toString() {
		return "Company [cmpyName=" + cmpyName + ", cmpyLocation=" + cmpyLocation + ", revenue=" + revenue + "]";
	}
	
}
