package com.appstek.dc.dbload;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_countries table.
 */
@Entity
@Table(name = "dbo.fnf_lan_countries")
public class FnfLanCountries implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long countryCode;
	private String countryName;
	private List<FnfLanStates> states = new ArrayList<FnfLanStates>();
 

	public FnfLanCountries() {
	}

	public FnfLanCountries(Long countryCode) {
		this.countryCode = countryCode;
	}

	public FnfLanCountries(Long countryCode, String countryName) {
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryCode")
	public List<FnfLanStates> getStates() {
		return states;
	}

	public void setStates(List<FnfLanStates> states) {
		this.states = states;
	}

}
