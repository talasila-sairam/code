package com.appstek.dc.dto;

import java.util.ArrayList;
import java.util.List;

import com.appstek.dc.dbload.FnfLanCountries;
import com.appstek.dc.dbload.FnfLanDictionary;
import com.appstek.dc.dbload.FnfLanStates;
import com.appstek.dc.dbload.RuleQuery;

public class DataConversionDto {

	private String slno;
	private String property1;
	private String property2;
	private Long countryFipsCode;
	private Long stateFipsCode;
	private Long countyFipsCode;
	private String ruleLevel;
	private String countyTableNames;
	private String columnName;
	private String columnType;
	private String columnSize;
	private String countyName;
	private String stateCode;
	private Integer fieldNumber;
	private String fieldName;
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	private Integer ruleQueryId;
	private String edition;
	private int pageSize;
	private int pageNumber;
	private List<FnfLanDictionary> fieldList = new ArrayList<FnfLanDictionary>();
	private List<CountryStateCountyDto> countryList = new ArrayList<CountryStateCountyDto>();
	private List<FnfLanStates> stateList = new ArrayList<FnfLanStates>();
	private RuleQuery rule = new RuleQuery();

	
	

	public RuleQuery getRule() {
		return rule;
	}

	public void setRule(RuleQuery rule) {
		this.rule = rule;
	}

	public List<FnfLanStates> getStateList() {
		return stateList;
	}

	public void setStateList(List<FnfLanStates> stateList) {
		this.stateList = stateList;
	}

	public List<CountryStateCountyDto> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryStateCountyDto> countryList) {
		this.countryList = countryList;
	}

	public List<FnfLanDictionary> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FnfLanDictionary> fieldList) {
		this.fieldList = fieldList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Integer getRuleQueryId() {
		return ruleQueryId;
	}

	public void setRuleQueryId(Integer ruleQueryId) {
		this.ruleQueryId = ruleQueryId;
	}

	public Integer getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(Integer fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	public String getProperty3() {
		return property3;
	}

	public void setProperty3(String property3) {
		this.property3 = property3;
	}

	public String getProperty4() {
		return property4;
	}

	public void setProperty4(String property4) {
		this.property4 = property4;
	}

	private String property3;
	private String property4;

	public Long getCountryFipsCode() {
		return countryFipsCode;
	}

	public void setCountryFipsCode(Long countryFipsCode) {
		this.countryFipsCode = countryFipsCode;
	}

	public Long getStateFipsCode() {
		return stateFipsCode;
	}

	public void setStateFipsCode(Long stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	public Long getCountyFipsCode() {
		return countyFipsCode;
	}

	public void setCountyFipsCode(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}
	
	public String getRuleLevel() {
		return ruleLevel;
	}

	public void setRuleLevel(String ruleLevel) {
		this.ruleLevel = ruleLevel;
	}

	public String getCountyTableNames() {
		return countyTableNames;
	}

	public void setCountyTableNames(String countyTableNames) {
		this.countyTableNames = countyTableNames;
	}

}
