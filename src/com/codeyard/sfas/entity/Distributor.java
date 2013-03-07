package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_distributors")
public class Distributor extends AbstractBaseEntity{
    
    
    @Column(name = "point_name")
    private String pointName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "contact_name")
    private String contactName;
    
    @Column(name = "contact_number")
    private String mobileNumber;

    @Column(name = "is_active")
    private boolean active;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="tso_id")
    private TSO tso;
        
    public Distributor(){
    	active = true;
    	tso = new TSO();    	
    }

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public TSO getTso() {
		return tso;
	}

	public void setTso(TSO tso) {
		this.tso = tso;
	}
    

}