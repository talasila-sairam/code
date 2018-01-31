package com.appstek.dc.dbload;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_states table.
 */
@Entity
@Table(name = "dbo.fnf_lan_states")
public class FnfLanStates implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fipsCode;
	private String stateCode;
	private String stateName;
	private FnfLanCountries countryCode;
	private List<FnfLanCounties> counties = new ArrayList<FnfLanCounties>();

	public FnfLanStates() {
	}

	public FnfLanStates(Long fipsCode) {
		this.fipsCode = fipsCode;
	}

	public FnfLanStates(Long fipsCode, String stateCode, String stateName, FnfLanCountries countryCode) {
		this.fipsCode = fipsCode;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.countryCode = countryCode;
	}

	@Id

	@Column(name = "FIPS_CODE", unique = true, nullable = false)
	public Long getFipsCode() {
		return this.fipsCode;
	}

	public void setFipsCode(Long fipsCode) {
		this.fipsCode = fipsCode;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_CODE")
	public FnfLanCountries getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(FnfLanCountries countryCode) {
		this.countryCode = countryCode;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_FIPS_CODE")
	public List<FnfLanCounties> getCounties() {
		return counties;
	}

	public void setCounties(List<FnfLanCounties> counties) {
		this.counties = counties;
	}

}
