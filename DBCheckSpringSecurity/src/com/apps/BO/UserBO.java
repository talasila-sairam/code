package com.apps.BO;

public class UserBO {
	protected String userName;
	protected String password;
	protected String role;
	protected String ocked;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String isOcked() {
		return ocked;
	}
	public void setOcked(String ocked) {
		this.ocked = ocked;
	}
	@Override
	public String toString() {
		return "UserBO [userName=" + userName + ", password=" + password + ", role=" + role + ", ocked=" + ocked + "]";
	}
	
}
