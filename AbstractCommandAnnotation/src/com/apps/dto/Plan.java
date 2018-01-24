package com.apps.dto;

public class Plan {
	protected String planName;
	protected double rechargePlan;
	protected double balanceAdded;
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public double getRechargePlan() {
		return rechargePlan;
	}
	public void setRechargePlan(double rechargePlan) {
		this.rechargePlan = rechargePlan;
	}
	public double getBalanceAdded() {
		return balanceAdded;
	}
	public void setBalanceAdded(double balanceAdded) {
		this.balanceAdded = balanceAdded;
	}
	@Override
	public String toString() {
		return "Plan [planName=" + planName + ", rechargePlan=" + rechargePlan + ", balanceAdded=" + balanceAdded + "]";
	}
	public Plan(String planName, double rechargePlan, double balanceAdded) {
		super();
		this.planName = planName;
		this.rechargePlan = rechargePlan;
		this.balanceAdded = balanceAdded;
	}
	
	
}
