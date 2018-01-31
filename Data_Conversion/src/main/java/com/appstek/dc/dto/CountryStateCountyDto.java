package com.appstek.dc.dto;

import java.io.Serializable;

import com.appstek.dc.dbload.FnfLanStates;

public class CountryStateCountyDto implements Serializable {
	
	public Long getFipsCode() {
		return fipsCode;
	}
	public void setFipsCode(Long fipsCode) {
		this.fipsCode = fipsCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	private Long countryCode;
	private String countryName;
	private Long fipsCode;
	private String stateCode;
	private String stateName;
	private Long countyFipsCode;
	private String countyName;
	public Long getCountyFipsCode() {
		return countyFipsCode;
	}
	public void setCountyFipsCode(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public Long getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
