package com.app.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="reciept")
public class Receipt {
	protected String accNo;
	protected double balance;
	public Receipt() {
		super();
		
	}

	protected double deductedAmount;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getDeductedAmount() {
		return deductedAmount;
	}

	public void setDeductedAmount(double deductedAmount) {
		this.deductedAmount = deductedAmount;
	}

	public Receipt(String accNo, double balance, double deductedAmount) {
		super();
		this.accNo = accNo;
		this.balance = balance;
		this.deductedAmount = deductedAmount;
	}

	@Override
	public String toString() {
		return "Receipt [accNo=" + accNo + ", balance=" + balance + ", deductedAmount=" + deductedAmount + "]";
	}

}
