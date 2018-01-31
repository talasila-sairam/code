package com.appstek.dc.dbload;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.fnf_lan_countries")
public class Country implements Serializable {
	private Long countryCode;
	private String countryName;
	
	@Id

	@Column(name = "COUNTRY_CODE", unique = true, nullable = false)
	public Long getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "COUNTRY_NAME", length = 100)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}
	
	
}
