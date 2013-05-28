package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_company_factories")
public class CompanyFactory extends AbstractBaseEntity{
    
    
    @Column(name = "factory_name")
    private String name;
    
    @Column(name = "factory_address")
    private String address;    
    
    @Column(name = "is_active")
    private boolean active;
    
        
    public CompanyFactory(){
    	active = true;
    }


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	   
}