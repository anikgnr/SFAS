package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_product_region_rates")
public class ProductRegionRate extends AbstractBaseEntity{
    
    
	@ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
	@ManyToOne(optional=true)
    @JoinColumn(name="region_id")
    private Region region;
    
    @Column(name = "rate")
    private Double rate;

    @Column(name = "profit_margin")
    private Double profitMargin;
    
        
    public ProductRegionRate(){
    
    	product = new Product();
    	region = new Region();
    	rate = 0.0;
    	profitMargin = 0.0;
    }


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
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

        
}