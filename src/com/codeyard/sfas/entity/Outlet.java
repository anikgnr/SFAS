package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_outlets")
public class Outlet extends AbstractBaseEntity{
    
    
    @Column(name = "outlet_name")
    private String pointName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "owner_name")
    private String ownerName;
    
    @Column(name = "owner_number")
    private String mobileNumber;

    @Column(name = "seller_name")
    private String sellerName;
    
    @Column(name = "seller_number")
    private String sellerNumber;

    @Column(name = "is_active")
    private boolean active;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="route_id")
    private Route route;
    
    public Outlet(){
    	active = true;
    	distributor = new Distributor();  
    	route = new Route();
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(String sellerNumber) {
		this.sellerNumber = sellerNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}