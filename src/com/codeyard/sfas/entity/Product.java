package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_products")
public class Product extends AbstractBaseEntity{
    
    
    @Column(name = "product_name")
    private String productName;
        
    @Column(name = "bag_size")
    private String bagSize;
    
    @Column(name = "rate")
    private Double rate;

    @Column(name = "profit_margin")
    private Double profitMargin;
    
    @Column(name = "product_description")
    private String productDescription;
    
    @Column(name = "is_active")
    private boolean active;
    
        
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(Double profitMargin) {
		this.profitMargin = profitMargin;
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
    
}