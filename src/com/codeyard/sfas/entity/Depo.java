package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

@Entity
@Table(name="cy_be_depos")
public class Depo extends AbstractBaseEntity{
    
    
    @Column(name = "depo_name")
    private String name;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "contact_name")
    private String contactName;
    
    @Column(name = "contact_number")
    private String mobileNumber;

    @Column(name = "is_active")
    private boolean active;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="rsm_id")
    private RSM rsm;
    
    @Column(name = "current_balance")
    private Double currentBalance;
    
    //company inventory is also going to be maintained as depo with this flag on
    @Column(name = "is_company_inventory")
    private boolean companyInventory;
    
    
    public Depo(){
    	active = true;
    	rsm = new RSM();
    	companyInventory = false;
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

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RSM getRsm() {
		return rsm;
	}

	public void setRsm(RSM rsm) {
		this.rsm = rsm;
	}

	public boolean isCompanyInventory() {
		return companyInventory;
	}

	public void setCompanyInventory(boolean companyInventory) {
		this.companyInventory = companyInventory;
	}
		
	public String getFullName(){
		if(this.companyInventory)
			return this.name;
		else
			return this.name+" ("+this.rsm.getRegion().getName()+")";
	}
	
	public String getStockLink(){
		return "<a href='./depoStockList.html?id="+super.getId()+"'>Stocks</a>";
	}
	public String getOrdersLink(){
		return "<a href=''>Orders</a>";
	}	
	public String getOrderLink(){
		return "<a href=''>Place Order</a>";
	}
	public String getDamageLink(){
		return "<a href='./depoDamageStockList.html?id="+super.getId()+"'>Damages</a>";
	}
	public String getPlanLink(){
		return "<a href=''>Product Plan</a>";
	}
	public String getDepositLink(){
		return "<a href='./depoDepositList.html?id="+super.getId()+"'>Deposits</a>";
	}
	public String getSalesLink(){
		return "<a href=''>Sales</a>";
	}	
	
}