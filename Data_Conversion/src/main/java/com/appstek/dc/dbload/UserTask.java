package com.appstek.dc.dbload;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_tasks")
public class UserTask {
	@Id
	@GenericGenerator(name="user_id" , strategy="increment")
	@GeneratedValue(generator="user_id")
	@Column(name="user_id")
	private int userid;
	
	@Column(name="USERNAME")
	  private String userName;
	@Column(name="COUNTY")
	   private String county;
	@Column(name="STATUS")
	   private String status;
	@Column(name="assignedtime")
	   private Date assignedtime	;
	@Column(name="processintime")
	   private String processintime;
	@Column(name="completedtime")
	   private String completedtime;
	@Column(name="comments")
	   private String comments;
	@Column(name="state")
	   private String state;
	@Column(name="editionyear")
	   private String editionYear;
	@Column(name="duration")
	   private String duration;
	@Column(name="signoff")
	   private String signoff;
	@Column(name="processCount")
	   private Integer count;
	@Column(name="county_state_code")
	   private String countyStateCode;
	@Column(name="table_count")
	   private String tableCount;
	public String getuserName(){
	   return userName;
	   }
	   
	   public void setuserName(String userName1){
	   this.userName = userName1;
	   }
	   
	   public String getcounty(){
	   return county;
	   
	   }
	   
	   public void setcounty(String county1){
	   this.county = county1;
	   }
	  public String getProcessingTime(){
		   return processintime;
		   }
		   
		   public void setProcessingTime(String processintime){
		   this.processintime = processintime;
		   }
		   public String getCompletedTime(){
			   return completedtime;
			   }
			   
			   public void setCompletedTime(String completedtime){
			   this.completedtime = completedtime;
			   }
			   public String getstatus(){
				   return status;
				   }
				   
				   public void setstatus(String status1){
				   this.status = status1;
				   }
				   public String getComments(){
					   return comments;
					   }
					   
					   public void setComments(String comments){
					   this.comments = comments;
					   }
					   public String getState(){
						   return state;
						   }
						   
						   public void setState(String state){
						   this.state = state;
						   }
						   public String getEditionYear(){
							   return editionYear;
							   }
							   
							   public void setEditionYear(String editionYear){
							   this.editionYear = editionYear;
							   }
							   
							   public Date getAssignedTime(){
							   return assignedtime;
							   }
							   
							public void setAssignedTime(Date date) {
								// TODO Auto-generated method stub
								this.assignedtime = date;
							}
							public String getDuration(){
								   return duration;
								   }
								   
								   public void setDuration(String duration){
								   this.duration = duration;
								   }
								   public String getSignOff(){
									   return signoff;
									}
									   
									   public void setSignOff(String signoff){
										   this.signoff = signoff;
									   }
									   public Integer getCount(){
										   return count;
										   }
										   
										   public void setCount(Integer count){
										   this.count = count;
										   }
										   public String getStateCounty(){
											   return countyStateCode;
											   }
											   
											   public void setStateCounty(String countyStateCode){
											   this.countyStateCode = countyStateCode;
											   }
											   public String getTableCount(){
												   return tableCount;
												   }
												   
												   public void setTableCount(String tableCount){
												   this.tableCount = tableCount;
												   }
}
