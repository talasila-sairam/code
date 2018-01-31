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
import javax.persistence.Transient;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_counties table.
 */
@Entity
@Table(name = "dbo.fnf_lan_counties")
public class FnfLanCounties implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long countyFipsCode;
	private FnfLanStates stateFipsCode;
	private String countyName;
	private Long countyId ;
	@Id
	@Column(name="COUNTY_ID", unique=true, nullable=false)
	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	private List<FnfLanCountyTables> countyTables = new ArrayList<FnfLanCountyTables>();
	
	@Transient
	private List<FnfLanDictionary> listOfFields = new ArrayList<FnfLanDictionary>();

	public FnfLanCounties() {
	}

	public FnfLanCounties(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}

	public FnfLanCounties(Long countyFipsCode, FnfLanStates stateFipsCode, String countyName) {
		this.countyFipsCode = countyFipsCode;
		this.stateFipsCode = stateFipsCode;
		this.countyName = countyName;
	}

	@Column(name = "COUNTY_FIPS_CODE",nullable = false)
	public Long getCountyFipsCode() {
		return this.countyFipsCode;
	}

	public void setCountyFipsCode(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_FIPS_CODE")
	public FnfLanStates getStateFipsCode() {
		return this.stateFipsCode;
	}

	public void setStateFipsCode(FnfLanStates stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	@Column(name = "COUNTY_NAME", length = 500)
	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countyFipsCode")
	public List<FnfLanCountyTables> getCountyTables() {
		return countyTables;
	}

	public void setCountyTables(List<FnfLanCountyTables> countyTables) {
		this.countyTables = countyTables;
	}
	@Transient
	public List<FnfLanDictionary> getListOfFields() {
		return listOfFields;
	}
	@Transient
	public void setListOfFields(List<FnfLanDictionary> listOfFields) {
		this.listOfFields = listOfFields;
	}

}
