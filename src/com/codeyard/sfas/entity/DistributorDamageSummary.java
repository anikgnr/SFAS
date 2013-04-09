package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_re_distributor_damaged_stocks")
public class DistributorDamageSummary extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name = "damage_type")
    private  String damageType;   
    
    @Column(name = "quantity")
    private  Long quantity;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    
    public DistributorDamageSummary(){
    	quantity = 0L;
    	product = new Product();
    	distributor = new Distributor();
    }


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getDamageType() {
		return damageType;
	}


	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Distributor getDistributor() {
		return distributor;
	}


	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

}