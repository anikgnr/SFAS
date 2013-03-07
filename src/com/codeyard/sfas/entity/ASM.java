package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_asms")
public class ASM extends AbstractBaseEntity{
    
    
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
    @JoinColumn(name="rsm_id")
    private RSM rsm;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="area_id")
    private Area area;
    

    public ASM(){
    	active = true;
    	rsm = new RSM();
    	area = new Area();
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
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public RSM getRsm() {
		return rsm;
	}
	public void setRsm(RSM rsm) {
		this.rsm = rsm;
	}
	

}