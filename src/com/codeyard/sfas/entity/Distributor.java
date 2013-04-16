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
    
    @ManyToOne(optional=true)
    @JoinColumn(name="depo_id")
    private Depo depo;
    
    @Column(name = "current_balance")
    private Double currentBalance;    
        
    public Distributor(){
    	active = true;
    	tso = new TSO();  
    	depo = new Depo();
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

	public Double getCurrentBalance() {
		if(currentBalance != null)
			return currentBalance;
		else
			return 0.0;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Depo getDepo() {
		return depo;
	}

	public void setDepo(Depo depo) {
		this.depo = depo;
	}
	
	public String getFullName(){
		return this.pointName+" ( "+this.tso.getTerritory().getName()+" )";
	}
	
	public String getStockLink(){
		return "<a href='./distributorStockList.html?id="+super.getId()+"'>Stocks</a>";
	}
	public String getOrdersLink(){
		return "<a href='./distributorOrderList.html?id="+super.getId()+"'>Orders</a>";
	}	
	public String getOrderLink(){
		return "<a href='./distributorOrder.html?did="+super.getId()+"'>Place Order</a>";		
	}
	public String getDamageLink(){
		return "<a href='./distributorDamageStockList.html?id="+super.getId()+"'>Damages</a>";
	}
	public String getPlanLink(){
		return "<a href='./distributorPlanSheet.html?id="+super.getId()+"'>Product Plan</a>";
	}
	public String getDepositLink(){
		return "<a href='./distributorDepositList.html?id="+super.getId()+"'>Deposits</a>";
	}
	public String getSalesLink(){
		return "<a href='./distributorSaleList.html?id="+super.getId()+"'>Sales</a>";		
	}	

}