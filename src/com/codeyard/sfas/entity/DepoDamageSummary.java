package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_re_depo_damaged_stocks")
public class DepoDamageSummary extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name = "damage_type")
    private  String damageType;   
    
    @Column(name = "quantity")
    private  Long quantity;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="depo_id")
    private Depo depo;
    
    
    public DepoDamageSummary(){
    	quantity = 0L;
    	product = new Product();
    	depo = new Depo();
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


	public Depo getDepo() {
		return depo;
	}


	public void setDepo(Depo depo) {
		this.depo = depo;
	}
	
}