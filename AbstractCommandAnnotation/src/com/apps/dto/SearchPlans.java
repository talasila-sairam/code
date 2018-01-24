package com.apps.dto;

public class SearchPlans {
	protected String pname;
	protected int pvalidity;
	protected double cost;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPvalidity() {
		return pvalidity;
	}
	public void setPvalidity(int pvalidity) {
		this.pvalidity = pvalidity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "SearchPlans [pname=" + pname + ", pvalidity=" + pvalidity + ", cost=" + cost + "]";
	}
	
}
