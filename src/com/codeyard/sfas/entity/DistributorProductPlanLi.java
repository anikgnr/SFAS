package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_re_distributor_product_plan_li")
public class DistributorProductPlanLi extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_product_plan_id")
    private DistributorProductPlan plan;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name = "quantity")
    private long quantity;
    
    @Column(name = "used")
    private long used;
    
        
    public DistributorProductPlanLi(){
    	plan = new DistributorProductPlan();
    	product = new Product();
    }

    public DistributorProductPlanLi(Long id, Long planId, Long productId, Long used, Long quantity){
    	super.setId(id);
    	this.used = used;
    	this.quantity = quantity;
    	plan = new DistributorProductPlan();
    	plan.setId(planId);
    	product = new Product();
    	product.setId(productId);
    }

	public DistributorProductPlan getPlan() {
		return plan;
	}


	public void setPlan(DistributorProductPlan plan) {
		this.plan = plan;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public long getUsed() {
		return used;
	}


	public void setUsed(long used) {
		this.used = used;
	}

	
	public long getBalance(){
		if((this.quantity - this.used) > 0)
			return this.quantity - this.used; 
		else
			return 0;
	}
}