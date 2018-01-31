package com.appstek.dc.dto;

public class FnfLanCounties {

	private String STATE_FIPS_CODE;
	private String COUNTY_NAME;
	private String COUNTY_FIPS_CODE;
	
	public FnfLanCounties(){}

	public String getSTATE_FIPS_CODE() {
		return STATE_FIPS_CODE;
	}

	public void setSTATE_FIPS_CODE(String sTATE_FIPS_CODE) {
		STATE_FIPS_CODE = sTATE_FIPS_CODE;
	}

	public String getCOUNTY_NAME() {
		return COUNTY_NAME;
	}

	public void setCOUNTY_NAME(String cOUNTY_NAME) {
		COUNTY_NAME = cOUNTY_NAME;
	}

	public String getCOUNTY_FIPS_CODE() {
		return COUNTY_FIPS_CODE;
	}

	public void setCOUNTY_FIPS_CODE(String cOUNTY_FIPS_CODE) {
		COUNTY_FIPS_CODE = cOUNTY_FIPS_CODE;
	}
	
}
