package com.apps.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="companyinputs")
public class CompanyInput {
	protected String cmpyLocation;
	protected String cmpyType;
	protected int level;
	public String getCmpyLocation() {
		return cmpyLocation;
	}
	public void setCmpyLocation(String cmpyLocation) {
		this.cmpyLocation = cmpyLocation;
	}
	public String getCmpyType() {
		return cmpyType;
	}
	public void setCmpyType(String cmpyType) {
		this.cmpyType = cmpyType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "CompanyInput [cmpyLocation=" + cmpyLocation + ", cmpyType=" + cmpyType + ", level=" + level + "]";
	}
	
}
