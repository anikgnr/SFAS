package com.codeyard.sfas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="cy_re_depo_order_li")
public class DepoOrderLi extends AbstractBaseEntity{
    
	@ManyToOne(optional=true)
    @JoinColumn(name="depo_order_id")
    private DepoOrder depoOrder;
    
	@ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
	@Column(name = "current_stock")
	private Long currentStock;
	
	@Column(name = "total_sale")
	private Long totalSale;
	
	@Column(name = "total_damage")
	private Long totalDamage;
	
	@Column(name = "order_quantity")
	private Long quantity;
	
	@Column(name = "product_rate")
	private Double currentRate;
	
	@Column(name = "product_profit_margin")
	private Double currentProfitMargin;
	
	@Column(name = "order_amount")
	private Double amount;
	
	@Column(name = "remark")
	private String remark;
	
	@Transient
	private int serial;
	
	public DepoOrderLi(){
    	this.depoOrder = new DepoOrder();
    	this.product = new Product();
    	this.currentStock = 0L;
    	this.totalSale = 0L;
    	this.totalDamage = 0L;
    	this.quantity = 0L;
    	this.currentRate = 0.0;
    	this.currentProfitMargin = 0.0;
    	this.amount = 0.0;    	
    }

    public DepoOrderLi(DepoOrder order, Product product){
    	this.depoOrder = order;
    	this.product = product;
    	this.currentStock = 0L;
    	this.totalSale = 0L;
    	this.totalDamage = 0L;
    	this.quantity = 0L;
    	this.currentRate = 0.0;
    	this.currentProfitMargin = 0.0;
    	this.amount = 0.0;    	
    }

	public DepoOrder getDepoOrder() {
		return depoOrder;
	}

	public void setDepoOrder(DepoOrder depoOrder) {
		this.depoOrder = depoOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Long currentStock) {
		this.currentStock = currentStock;
	}

	public Long getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(Long totalSale) {
		this.totalSale = totalSale;
	}

	public Long getTotalDamage() {
		return totalDamage;
	}

	public void setTotalDamage(Long totalDamage) {
		this.totalDamage = totalDamage;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(Double currentRate) {
		this.currentRate = currentRate;
	}

	public Double getCurrentProfitMargin() {
		return currentProfitMargin;
	}

	public void setCurrentProfitMargin(Double currentProfitMargin) {
		this.currentProfitMargin = currentProfitMargin;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

    
}