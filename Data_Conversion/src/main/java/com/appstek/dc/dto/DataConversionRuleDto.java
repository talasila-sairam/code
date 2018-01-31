package com.appstek.dc.dto;

import java.util.ArrayList;
import java.util.List;

public class DataConversionRuleDto {

	private int countryFipsCode;
	private int stateFipsCode;
	private int countyFipsCode;
	private String ruleQuery;
	//private String fieldNumber;
	private int fieldNumber;
	private String countryName;
	private String selectedFieldName;
	private String selectedFieldNumber;
	private String selectedFieldValue;
	public String getSelectedFieldName() {
		return selectedFieldName;
	}
	public void setSelectedFieldName(String selectedFieldName) {
		this.selectedFieldName = selectedFieldName;
	}
	public String getSelectedFieldNumber() {
		return selectedFieldNumber;
	}
	public void setSelectedFieldNumber(String selectedFieldNumber) {
		this.selectedFieldNumber = selectedFieldNumber;
	}
	public String getSelectedFieldValue() {
		return selectedFieldValue;
	}
	public void setSelectedFieldValue(String selectedFieldValue) {
		this.selectedFieldValue = selectedFieldValue;
	}
	private String stateName;
	private String countyName;
	private String ruleScope;
	private String ruleName;
	private int ruleQueryId;
	private String query;
	private List<Integer> fieldList;
	private String baseQuery;
	private String fieldName;
	private String tables;
	private String columns;
	private String edition;
	private String ruleQuery71;
	private String ruleQuery72;
	private String ruleQuery73;
	private List rowDataArr_71_72_73 = new ArrayList();
	private String ruleExcelFilePath;
	private int ruleId;
	private List rowDataArr_ForIndividual=new ArrayList<>();
	private String stateCode;
	private String buildbasequery;
	private String buildrulequery;
	private String ruleColumn;
	private String buildRuleQuery71;
	private String buildRuleQuery72;
	private String buildRuleQuery73;
	private String mappingFieldName;
	
	public String getMappingFieldName() {
		return mappingFieldName;
	}
	public void setMappingFieldName(String mappingFieldName) {
		this.mappingFieldName = mappingFieldName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleFieldName(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleFieldName() {
		return fieldName;
	}
	public void setRuleId(String ruleId) {
		this.fieldName = fieldName;
	}
	public String getRuleExcelFilePath() {
		return ruleExcelFilePath;
	}
	public void setRuleExcelFilePath(String ruleExcelFilePath) {
		this.ruleExcelFilePath = ruleExcelFilePath;
	}
	public String getRuleQuery71() {
		return ruleQuery71;
	}
	public void setRuleQuery71(String ruleQuery71) {
		this.ruleQuery71 = ruleQuery71;
	}
	public String getRuleQuery72() {
		return ruleQuery72;
	}
	public void setRuleQuery72(String ruleQuery72) {
		this.ruleQuery72 = ruleQuery72;
	}
	public String getRuleQuery73() {
		return ruleQuery73;
	}
	public void setRuleQuery73(String ruleQuery73) {
		this.ruleQuery73 = ruleQuery73;
	}
	public List getRowDataArr_71_72_73() {
		return rowDataArr_71_72_73;
	}
	public void setRowDataArr_71_72_73(List rowDataArr_71_72_73) {
		this.rowDataArr_71_72_73 = rowDataArr_71_72_73;
	}
	public List getRowDataArr_ForIndividual() {
		return rowDataArr_ForIndividual;
	}
	public void setRowDataArr_ForIndividual(List rowDataArr_ForIndividual) {
		this.rowDataArr_ForIndividual = rowDataArr_ForIndividual;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getBaseQuery() {
		return baseQuery;
	}
	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}
	public List<Integer> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<Integer> fieldList) {
		this.fieldList = fieldList;
	}
	public int getRuleQueryId() {
		return ruleQueryId;
	}
	public void setRuleQueryId(int ruleQueryId) {
		this.ruleQueryId = ruleQueryId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleScope() {
		return ruleScope;
	}
	public void setRuleScope(String ruleScope) {
		this.ruleScope = ruleScope;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public int getFieldNumber() {
		return fieldNumber;
	}
	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}
	public int getCountryFipsCode() {
		return countryFipsCode;
	}
	public void setCountryFipsCode(int countryFipsCode) {
		this.countryFipsCode = countryFipsCode;
	}
	public int getStateFipsCode() {
		return stateFipsCode;
	}
	public void setStateFipsCode(int stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}
	public int getCountyFipsCode() {
		return countyFipsCode;
	}
	public void setCountyFipsCode(int countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}
	public String getRuleQuery() {
		return ruleQuery;
	}
	public void setRuleQuery(String ruleQuery) {
		this.ruleQuery = ruleQuery;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getBuildbasequery() {
		return buildbasequery;
	}
	public void setBuildbasequery(String buildbasequery) {
		this.buildbasequery = buildbasequery;
	}
	public String getBuildrulequery() {
		return buildrulequery;
	}
	public void setBuildrulequery(String buildrulequery) {
		this.buildrulequery = buildrulequery;
	}
	public String getRuleColumn() {
		return ruleColumn;
	}
	public void setRuleColumn(String ruleColumn) {
		this.ruleColumn = ruleColumn;
	}
	public String getBuildRuleQuery71() {
		return buildRuleQuery71;
	}
	public void setBuildRuleQuery71(String buildRuleQuery71) {
		this.buildRuleQuery71 = buildRuleQuery71;
	}
	public String getBuildRuleQuery72() {
		return buildRuleQuery72;
	}
	public void setBuildRuleQuery72(String buildRuleQuery72) {
		this.buildRuleQuery72 = buildRuleQuery72;
	}
	public String getBuildRuleQuery73() {
		return buildRuleQuery73;
	}
	public void setBuildRuleQuery73(String buildRuleQuery73) {
		this.buildRuleQuery73 = buildRuleQuery73;
	}
}