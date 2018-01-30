package com.apps.BO;

public class ProductForm {
	protected String source;
	protected String faddress;
	protected String fmobile;
	protected String destination;
	protected String taddress;
	protected String tmobile;
	protected String pname;
	protected double pprice;
	protected int pweight;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getFmobile() {
		return fmobile;
	}
	public void setFmobile(String fmobile) {
		this.fmobile = fmobile;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTaddress() {
		return taddress;
	}
	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}
	public String getTmobile() {
		return tmobile;
	}
	public void setTmobile(String tmobile) {
		this.tmobile = tmobile;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPprice() {
		return pprice;
	}
	public void setPprice(double pprice) {
		this.pprice = pprice;
	}
	public int getPweight() {
		return pweight;
	}
	public void setPweight(int pweight) {
		this.pweight = pweight;
	}
	@Override
	public String toString() {
		return "ProductForm [source=" + source + ", faddress=" + faddress + ", fmobile=" + fmobile + ", destination="
				+ destination + ", taddress=" + taddress + ", tmobile=" + tmobile + ", pname=" + pname + ", pprice="
				+ pprice + ", pweight=" + pweight + "]";
	}
	
}
