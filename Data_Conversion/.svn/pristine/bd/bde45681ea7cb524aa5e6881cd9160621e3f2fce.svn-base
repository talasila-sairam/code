package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.fnf_lan_states")
public class State {
	private Long stateFipsCode;
	private String stateCode;
	private String stateName;
	private Long countryCode;
	
	@Id

	@Column(name = "FIPS_CODE", unique = true, nullable = false)
	public Long getStateFipsCode() {
		return this.stateFipsCode;
	}

	public void setStateFipsCode(Long stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	@Column(name = "STATE_CODE", length = 500)
	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name = "STATE_NAME", length = 500)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	@Column(name = "COUNTRY_CODE")
	public Long getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "State [stateFipsCode=" + stateFipsCode + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", countryCode=" + countryCode + "]";
	}

}
