package com.codeyard.sfas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cy_re_stocks_in")
public class StockIn extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name = "manufacture_date")
    @Temporal(TemporalType.DATE)
    private  Date manufactureDate;
    
    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private  Date expireDate;
    
    @Column(name = "quantity")
    private  Long quantity;
    
    @Column(name = "stock_description")
    private  String comment;
    
    @Column(name = "is_damaged")
    private  boolean damaged;
    
    @Column(name = "damage_type")
    private  String damageType;    
    
    @Column(name = "damage_reason")
    private  String damageReason;    
    
    public StockIn(){
    	quantity = 0L;
    	product = new Product();
    	damaged = false;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public boolean isDamaged() {
		return damaged;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	public String getDamageReason() {
		return damageReason;
	}

	public void setDamageReason(String damageReason) {
		this.damageReason = damageReason;
	}
    

}