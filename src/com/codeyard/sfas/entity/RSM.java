package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import com.codeyard.sfas.util.Utils;

@Entity
@Table(name="cy_be_rsms")
public class RSM extends AbstractBaseEntity{
    
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "address")
    private String address;

    @ManyToOne(optional=true)
    @JoinColumn(name="region_id")
    private Region region;
    
    @Column(name = "monthly_gross")
    private Double monthlyGross;


    public RSM(){
    	active = true;
    	region = new Region();
    }
    

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getName() {
		if(Utils.isNullOrEmpty(firstName) && Utils.isNullOrEmpty(lastName))
			return "";
		return firstName+" "+lastName;
	}
	public String getNameWithRegion() {
		return firstName+" "+lastName+" ("+this.region.getName()+")";
	}	
	public Double getMonthlyGross() {
		return monthlyGross;
	}
	public void setMonthlyGross(Double monthlyGross) {
		this.monthlyGross = monthlyGross;
	}
	
}