package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LOGIN_CREDENTIALS")
public class LoginCredentials {
	@Id
	@GenericGenerator(name="EMP_ID" , strategy="increment")
	@GeneratedValue(generator="EMP_ID")
	@Column(name="EMP_ID", unique = true, nullable = false)
	private int empid;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	@Column(name="USER_NAME")
	  private String userName;
	@Column(name="PASSWORD")
	   private String password;
	@Column(name="MENU_ACCESS")
	   private String menuAccess;
	   public String getuserName(){
	   return userName;
	   }
	   
	   public void setuserName(String userName){
	   this.userName = userName;
	   }
	   
	   public String getPassword(){
	   return password;
	   
	   }
	   
	   public void setPassword(String password){
	   this.password = password;
	   }
	   public String getMenuaccess(){
		   return menuAccess;
		   
		   }
		   
		   public void setMenuaccess(String menuAccess){
		   this.menuAccess = menuAccess;
		   }

		 
   }
