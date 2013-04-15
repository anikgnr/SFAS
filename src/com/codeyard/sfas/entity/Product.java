package com.codeyard.sfas.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

@Entity
@Table(name="cy_be_products")
public class Product extends AbstractBaseEntity{
    
    
    @Column(name = "product_name")
    private String productName;
        
    @Column(name = "bag_size")
    private String bagSize;
    
    @Column(name = "product_description")
    private String productDescription;
    
    @Column(name = "is_active")
    private boolean active;
    
    @Transient
    private List<ProductRegionRate> regionalRateList; 
        
    public Product(){
    	active = true;
    }

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBagSize() {
		return bagSize;
	}

	public void setBagSize(String bagSize) {
		this.bagSize = bagSize;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getFullName(){
		return this.productName+" "+this.bagSize+" pcs";
	}
	
	public String getRateLink(){
		return "<a href='./setupRegionalRates.html?id="+super.getId()+"'>Setup Rates</a>";
	}

	public List<ProductRegionRate> getRegionalRateList() {
		return regionalRateList;
	}

	public void setRegionalRateList(List<ProductRegionRate> regionalRateList) {
		this.regionalRateList = regionalRateList;
	}
 
	
}