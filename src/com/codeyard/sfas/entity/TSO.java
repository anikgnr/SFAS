package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

@Entity
@Table(name="cy_be_tsos")
public class TSO extends AbstractBaseEntity{
    
    
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
    @JoinColumn(name="asm_id")
    private ASM asm;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="territory_id")
    private Territory territory;
    
    public TSO(){
    	active = true;
    	asm = new ASM();
    	territory = new Territory();
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

	public ASM getAsm() {
		return asm;
	}
	public void setAsm(ASM asm) {
		this.asm = asm;
	}
	public Territory getTerritory() {
		return territory;
	}
	public void setTerritory(Territory territory) {
		this.territory = territory;
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
	public String getName() {
		return firstName+" "+lastName;
	}

}