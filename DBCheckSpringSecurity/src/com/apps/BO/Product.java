package com.apps.BO;

public class Product {
	protected String pname;
	protected double pprice;
	protected int pweight;
	protected FromBO fromBo;
	protected ToBO toBO;
	public FromBO getFromBo() {
		return fromBo;
	}
	public void setFromBo(FromBO fromBo) {
		this.fromBo = fromBo;
	}
	public ToBO getToBO() {
		return toBO;
	}
	public void setToBO(ToBO toBO) {
		this.toBO = toBO;
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
	
}
