package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_county_tables table.
 */
@Entity
@Table(name = "dbo.fnf_lan_county_tables")
public class FnfLanCountyTables implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tableId;
	private Integer stateFipsCode;
	private FnfLanCounties countyFipsCode;
	private String oracleTableName;
	private String otherTableName;
	private String mainFlag;
	@Transient
	private int tableRecordCount;
	@Transient
	public int getTableRecordCount() {
		return tableRecordCount;
	}
	@Transient
	public void setTableRecordCount(int tableRecordCount) {
		this.tableRecordCount = tableRecordCount;
	}

	public FnfLanCountyTables() {
	}

	public FnfLanCountyTables(int tableId) {
		this.tableId = tableId;
	}

	public FnfLanCountyTables(int tableId, Integer stateFipsCode, FnfLanCounties countyFipsCode, String oracleTableName,
			String otherTableName, String mainFlag) {
		this.tableId = tableId;
		this.stateFipsCode = stateFipsCode;
		this.countyFipsCode = countyFipsCode;
		this.oracleTableName = oracleTableName;
		this.otherTableName = otherTableName;
		this.mainFlag = mainFlag;
	}

	@Id

	@Column(name = "TABLE_ID", unique = true, nullable = false)
	public int getTableId() {
		return this.tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	@Column(name = "STATE_FIPS_CODE")
	public Integer getStateFipsCode() {
		return this.stateFipsCode;
	}

	public void setStateFipsCode(Integer stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTY_FIPS_CODE")
	public FnfLanCounties getCountyFipsCode() {
		return countyFipsCode;
	}

	public void setCountyFipsCode(FnfLanCounties countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}

	@Column(name = "ORACLE_TABLE_NAME", length = 500)
	public String getOracleTableName() {
		return this.oracleTableName;
	}

	public void setOracleTableName(String oracleTableName) {
		this.oracleTableName = oracleTableName;
	}

	@Column(name = "OTHER_TABLE_NAME", length = 500)
	public String getOtherTableName() {
		return this.otherTableName;
	}

	public void setOtherTableName(String otherTableName) {
		this.otherTableName = otherTableName;
	}

	@Column(name = "MAIN_FLAG", length = 20)
	public String getMainFlag() {
		return this.mainFlag;
	}

	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}

}
