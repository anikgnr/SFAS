package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_inventories")
public class Inventory extends AbstractBaseEntity{
    
    
    @Column(name = "inventory_name")
    private String name;
        
    @Column(name = "location")
    private String address;
    
        
    @Column(name = "is_active")
    private boolean active;
    
        
    public Inventory(){
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