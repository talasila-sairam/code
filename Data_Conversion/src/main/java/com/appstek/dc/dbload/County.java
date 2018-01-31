package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.fnf_lan_counties")
public class County {
	private Long countyFipsCode;
	private String countyName;
	private Long countyId ;
	private Long stateFipsCode;
	
	@Id
	@Column(name="COUNTY_ID", unique=true, nullable=false)
	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	
	@Column(name = "COUNTY_FIPS_CODE",nullable = false)
	public Long getCountyFipsCode() {
		return this.countyFipsCode;
	}

	public void setCountyFipsCode(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}
	
	@Column(name = "COUNTY_NAME", length = 500)
	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	@Column(name = "STATE_FIPS_CODE")
	public Long getStateFipsCode() {
		return this.stateFipsCode;
	}

	public void setStateFipsCode(Long stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	@Override
	public String toString() {
		return "County [countyFipsCode=" + countyFipsCode + ", countyName=" + countyName + ", countyId=" + countyId
				+ ", stateFipsCode=" + stateFipsCode + "]";
	}
	
}
