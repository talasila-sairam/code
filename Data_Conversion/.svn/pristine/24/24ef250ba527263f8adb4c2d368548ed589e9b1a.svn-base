package com.appstek.dc.dbload;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

/**
 * @author Sushanta
 * 
 * Database mapping entity class for territory table.
 */
@Entity
@Table(name = "dbo.territory")
@Proxy(lazy = false)
public class Territory {
	@Id
	@GenericGenerator(name="rulMstr" , strategy="increment")
	@GeneratedValue(generator="rulMstr")
	@Column(name="TERRITORY_ID")
	private int territoryId;
	
	@Column(name="TERRITORY_NAME")
	private String territoryName;
	
	@Column(name="FIPS_CODE")
	private int fipsCode;
	
	//@Column(name="PARENT_ID")
	//private int parentId;
	
	@Column(name="TERRITORY_TYPE")
	private String territoryType;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "territory")
	private Set<RuleMaster> ruleMasters = new HashSet<RuleMaster>();
	
	public Set<RuleMaster> getRuleMasters() {
		return ruleMasters;
	}

	public void setRuleMasters(Set<RuleMaster> ruleMasters) {
		this.ruleMasters = ruleMasters;
	}*/
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PARENT_ID")
	private Territory parentTerritory;

	public int getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(int territoryId) {
		this.territoryId = territoryId;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	

	public int getFipsCode() {
		return fipsCode;
	}

	public void setFipsCode(int fipsCode) {
		this.fipsCode = fipsCode;
	}

	/*public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}*/

	public String getTerritoryType() {
		return territoryType;
	}

	public Territory getParentTerritory() {
		return parentTerritory;
	}

	public void setParentTerritory(Territory parentTerritory) {
		this.parentTerritory = parentTerritory;
	}

	public void setTerritoryType(String territoryType) {
		this.territoryType = territoryType;
	}
	
}
