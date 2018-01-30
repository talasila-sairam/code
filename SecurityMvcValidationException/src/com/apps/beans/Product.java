package com.apps.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Products")
public class Product implements Serializable{
	private static final long serialVersionUID = -1000119078147252957L;
	 
    private String code;
    private String name;
    private double cost;
    private byte[] image;
    private Date createDate;
    private int quantity;
    @Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Id
    @Column(name = "Code", length = 20, nullable = false)
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    @Column(name = "Name", length = 255, nullable = false)
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Column(name = "Price", nullable = false)
    public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
    @Temporal(TemporalType.DATE)
    @Column(name="create_Date",nullable=false)
    public Date getCreateDate() {
        return createDate;
    }
 
    

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @Lob
    @Column(name="Image",nullable=false,length=Integer.MAX_VALUE)
    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }
}
